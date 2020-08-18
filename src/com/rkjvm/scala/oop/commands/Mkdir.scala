package com.rkjvm.scala.oop.commands

import com.rkjvm.scala.oop.files.{DirEntry, Directory}
import com.rkjvm.scala.oop.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }


  def doMkdir(state: State, name: String): State = {
    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head)
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry.asDirectory, path.tail, newEntry))
      }



    }

    val wd = state.wd

    //1. all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    //2. create a new directory entry in the wd
    val newDir = Directory.empty(wd.path, name)

    //3.update all directory structure starting from the root
    val newRoot = updateStructure(state.root, allDirsInPath, newDir)

    //4. find the new working directory INSTANCE given wd's full path, in the NEW directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)

  }

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry " + " already exists!")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + "  must not contains separators!")
    } else if (checkIllegal(name)) {
      state.setMessage(name + " : illegal entry name!")
    } else {
      doMkdir(state, name)
    }
  }


}
