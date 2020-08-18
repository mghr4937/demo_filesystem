package com.rkjvm.scala.oop.commands
import com.rkjvm.scala.oop.files.Directory
import com.rkjvm.scala.oop.filesystem.State

class Mkdir(name: String) extends Command {

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doMkdir(state: State, name: String): State = {
    ???
  }

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)){
      state.setMessage("Entry " + " already exists!")
    }else if (name.contains(Directory.SEPARATOR)){
      state.setMessage(name + "  must not contains separators!")
    }else if (checkIllegal(name)) {
      state.setMessage(name + " : illegal entry name!")
    }else {
      doMkdir(name)
    }
  }


}
