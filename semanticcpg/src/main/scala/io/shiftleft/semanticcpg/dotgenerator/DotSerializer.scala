package io.shiftleft.semanticcpg.dotgenerator

import io.shiftleft.codepropertygraph.generated.nodes._
import io.shiftleft.semanticcpg.language._
import io.shiftleft.semanticcpg.utils.MemberAccess

object DotSerializer {

  case class Graph(vertices: List[StoredNode], edges: List[Edge]) {

    def ++(other: Graph): Graph = {
      Graph((this.vertices ++ other.vertices).distinct, (this.edges ++ other.edges).distinct)
    }

  }
  case class Edge(src: StoredNode,
                  dst: StoredNode,
                  srcVisible: Boolean = true,
                  label: String = "",
                  edgeType: String = "")

  def dotGraph(root: AstNode, graph: Graph, withEdgeTypes: Boolean = false): String = {
    val sb = DotSerializer.namedGraphBegin(root)
    val nodeStrings = graph.vertices.map(DotSerializer.nodeToDot)
    val edgeStrings = graph.edges.map(e => DotSerializer.edgeToDot(e, withEdgeTypes))
    sb.append((nodeStrings ++ edgeStrings).mkString("\n"))
    DotSerializer.graphEnd(sb)
  }

  private def namedGraphBegin(root: AstNode): StringBuilder = {
    val sb = new StringBuilder
    val name = root match {
      case method: Method => method.name
      case _              => ""
    }
    sb.append(s"""digraph "$name" {  \n""")
  }


  // Gets a location string for this node, delimited by ":"
  // Some nodes are missing some fields, so do your best to get a useful string
  implicit class StoredNodeWithLocationString(node: StoredNode) {
    // This type of node can be turned into a location string line:column:length
    type PartiallyLocationizable = StoredNode with HasLineNumber with HasColumnNumber with HasCode
    // This type of node can be turned into a location string line:column:lineend:columnend
    type FullyLocationizable = PartiallyLocationizable with HasLineNumberEnd with HasColumnNumberEnd

    def getLocationString(): String = {
      node match {
        case node: FullyLocationizable => List(node.lineNumber, node.columnNumber, node.lineNumberEnd, node.columnNumber).flatten.mkString(":")
        case node: PartiallyLocationizable => (List(node.lineNumber, node.columnNumber).flatten ++ List(node.code.length())).mkString(":")
        case _ => ""
      }
    }
  }


  private def stringRepr(vertex: StoredNode): String = {
    escape(
      vertex match {
        case call: Call               => (call.name, call.code, call.getLocationString()).toString
        case expr: Expression         => (expr.label, expr.code, toCfgNode(expr).code, expr.getLocationString()).toString
        case method: Method           => (method.label, method.name, method.getLocationString()).toString
        case ret: MethodReturn        => (ret.label, ret.typeFullName, ret.getLocationString()).toString
        case param: MethodParameterIn => ("PARAM", param.code, param.getLocationString()).toString
        case local: Local             => (local.label, s"${local.code}: ${local.typeFullName}", local.getLocationString()).toString
        case target: JumpTarget       => (target.label, target.name, target.getLocationString()).toString
        case _                        => ""
      }
    )
  }

  private def toCfgNode(node: StoredNode): CfgNode = {
    node match {
      case node: Identifier        => node.parentExpression.get
      case node: MethodRef         => node.parentExpression.get
      case node: Literal           => node.parentExpression.get
      case node: MethodParameterIn => node.method
      case node: MethodParameterOut =>
        node.method.methodReturn
      case node: Call if MemberAccess.isGenericMemberAccessName(node.name) =>
        node.parentExpression.get
      case node: CallRepr     => node
      case node: MethodReturn => node
      case node: Expression   => node
    }
  }

  private def nodeToDot(node: StoredNode): String = {
    s""""${node.id}" [label = "${DotSerializer.stringRepr(node)}" ]""".stripMargin
  }

  private def edgeToDot(edge: Edge, withEdgeTypes: Boolean): String = {
    val edgeLabel = if (withEdgeTypes) {
      edge.edgeType + ": " + DotSerializer.escape(edge.label)
    } else {
      DotSerializer.escape(edge.label)
    }
    val labelStr = Some(s""" [ label = "$edgeLabel"] """).filter(_ => edgeLabel != "").getOrElse("")
    s"""  "${edge.src.id}" -> "${edge.dst.id}" """ + labelStr
  }

  private def escape(str: String): String = {
    if (str == null) {
      ""
    } else {
      str.replace("\"", "\\\"")
    }
  }

  private def graphEnd(sb: StringBuilder): String = {
    sb.append("\n}\n")
    sb.toString
  }

}
