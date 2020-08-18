package com.rkjvm.scala.oop.commands
import com.rkjvm.scala.oop.filesystem.State

class UnknownCommand extends Command {

  override def apply(state: State): State =
    state.setMessage("Command not found!")


}
