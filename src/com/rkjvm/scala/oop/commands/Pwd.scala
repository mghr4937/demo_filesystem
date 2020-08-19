package com.rkjvm.scala.oop.commands
import com.rkjvm.scala.oop.filesystem.State

class Pwd extends Command {

  override def apply(state: State): State =
    state.setMessage(state.wd.path)


}
