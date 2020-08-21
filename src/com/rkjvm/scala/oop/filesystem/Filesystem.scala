package com.rkjvm.scala.oop.filesystem

import com.rkjvm.scala.oop.commands.Command
import com.rkjvm.scala.oop.files.Directory

object Filesystem extends App {

  val root = Directory.ROOT

  io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
    currentState.show
    Command.from(newLine).apply(currentState)
  })
}
