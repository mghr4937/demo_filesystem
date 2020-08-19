package com.rkjvm.scala.oop.commands

import com.rkjvm.scala.oop.files.{DirEntry, Directory}
import com.rkjvm.scala.oop.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path, name)
}
