package com.dant3.paperboy

import java.io.File

import com.dant3.paperboy.util.NSLog
import org.robovm.apple.foundation.{NSSearchPathDomainMask, NSPathUtilities, NSFileManager, NSSearchPathDirectory}
import scala.collection.JavaConversions._

object Cache {
  lazy val cacheRootDirectory = findCacheDirectory.map(new File(_)).map(mkdirs)


  private def mkdirs(file: File): File = { file.mkdirs(); file }

  def findCacheDirectory = {
    val paths = NSPathUtilities.getSearchPathForDirectoriesInDomains(
      NSSearchPathDirectory.CachesDirectory, NSSearchPathDomainMask.UserDomainMask, true
    )
    NSLog(s"Found cache dirs: $paths")
    val fileManager = NSFileManager.getDefaultManager
    paths.find(fileManager.fileIsWritable)
  }
}
