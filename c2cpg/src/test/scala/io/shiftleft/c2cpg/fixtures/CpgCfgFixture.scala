package io.shiftleft.c2cpg.fixtures

import better.files.File
import io.shiftleft.c2cpg.C2Cpg.Config
import io.shiftleft.c2cpg.passes.AstCreationPass
import io.shiftleft.codepropertygraph.Cpg
import io.shiftleft.codepropertygraph.generated.nodes.CfgNode
import io.shiftleft.passes.IntervalKeyPool
import io.shiftleft.semanticcpg.language._
import io.shiftleft.semanticcpg.passes.CfgCreationPass
import io.shiftleft.semanticcpg.passes.cfgcreation.Cfg.CfgEdgeType

import scala.jdk.CollectionConverters._

class CpgCfgFixture(code: String) {

  private val cpg: Cpg = Cpg.emptyCpg

  File.usingTemporaryDirectory("c2cpgtest") { dir =>
    val file1 = dir / "file1.c"
    file1.write(s"RET func() { $code }")
    val keyPoolFile1 = new IntervalKeyPool(1001, 2000)
    val filenames = List(file1.path.toAbsolutePath.toString)
    new AstCreationPass(filenames, cpg, keyPoolFile1, Config()).createAndApply()
    new CfgCreationPass(cpg).createAndApply()
  }

  val codeToNode: Map[String, CfgNode] =
    cpg.method.ast.isCfgNode.l.map { node =>
      node.code -> node
    }.toMap

  def expected(pairs: (String, CfgEdgeType)*): Set[String] = {
    pairs.map {
      case (code, _) => codeToNode(code).code
    }.toSet
  }

  def succOf(code: String): Set[String] = {
    codeToNode(code)._cfgOut.asScala
      .map(_.asInstanceOf[CfgNode])
      .toSet
      .map[String](_.code)
  }

}
