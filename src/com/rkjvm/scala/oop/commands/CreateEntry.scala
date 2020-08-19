package com.rkjvm.scala.oop.commands

import com.rkjvm.scala.oop.files.{DirEntry, Directory}
import com.rkjvm.scala.oop.filesystem.State

abstract class CreateEntry(name: String) extends Command {

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doCreateEntry(state: State, name: String): State = {
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
    //val newDir = Directory.empty(wd.path, name)
    val newEntry: DirEntry = createSpecificEntry(state)

    //3.update all directory structure starting from the root
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    //4. find the new working directory INSTANCE given wd's full path, in the NEW directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State): DirEntry

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry " + " already exists!")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + "  must not contains separators!")
    } else if (checkIllegal(name)) {
      state.setMessage(name + " : illegal entry name!")
    } else {
      doCreateEntry(state, name)
    }
  }


}