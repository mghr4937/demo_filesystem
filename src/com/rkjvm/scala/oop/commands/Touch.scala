package com.rkjvm.scala.oop.commands

import com.rkjvm.scala.oop.files.{DirEntry, File}
import com.rkjvm.scala.oop.filesystem.State

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)


}
