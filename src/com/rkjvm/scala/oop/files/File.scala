package com.rkjvm.scala.oop.files

import java.nio.file.FileSystemException

import com.rkjvm.scala.oop.filesystem.FilesystemException

class File(override val parentPath: String, override val name: String, content: String)
  extends DirEntry(parentPath, name) {

  override def asDirectory: Directory =
    throw new FilesystemException("File cannot be converted to a directory")

  override def getType: String = "File"
  override def asFile: File = this
  override def isDirectory: Boolean = false
  override def isFile: Boolean = true
}

object File {

  def empty(parentPath: String, name: String): File =
    new File(parentPath, name, "")

}
