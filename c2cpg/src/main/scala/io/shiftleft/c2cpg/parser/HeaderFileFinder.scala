package io.shiftleft.c2cpg.parser

import better.files._
import io.shiftleft.x2cpg.SourceFiles
import org.jline.utils.Levenshtein

import java.nio.file.{FileSystems, Path}

class HeaderFileFinder(roots: List[String]) {

  private val headerExtensions = List(".h", ".hpp", ".hh")
  private val nameToPathMap: Map[String, List[Path]] = {

    SourceFiles
      .determine(roots.toSet, headerExtensions.toSet)
      .map { p =>
        val file = File(p)
        (file.name, file.path)
      }
      .groupBy(_._1)
      .map(x => (x._1, x._2.map(_._2)))
  }

  /**
    * Given an unresolved header file, given as a non-existing absolute path,
    * determine whether a header file with the same name can be found anywhere
    * in the code base.
    * */
  def find(path: String): String = {
    path
      .split(FileSystems.getDefault.getSeparator)
      .lastOption
      .flatMap { name =>
        val matches = nameToPathMap.getOrElse(name, List())
        matches.map(_.toString).sortBy(x => Levenshtein.distance(x, path)).headOption
      }
      .orNull
  }

}