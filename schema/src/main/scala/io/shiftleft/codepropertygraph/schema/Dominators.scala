package io.shiftleft.codepropertygraph.schema

import overflowdb.schema.{SchemaBuilder, SchemaInfo}

object Dominators extends SchemaBase {

  def index: Int = 6

  override def description: String =
    """
      |""".stripMargin

  def apply(builder: SchemaBuilder, methodSchema: Method.Schema, ast: Ast.Schema) =
    new Schema(builder, methodSchema, ast)

  class Schema(builder: SchemaBuilder, methodSchema: Method.Schema, ast: Ast.Schema) {
    implicit private val schemaInfo = SchemaInfo.forClass(getClass)
    import methodSchema._
    import ast._

    val dominate = builder
      .addEdgeType(
        name = "DOMINATE",
        comment = "Points to dominated node in DOM tree"
      )
      .protoId(181)

    val postDominate = builder
      .addEdgeType(
        name = "POST_DOMINATE",
        comment = "Points to dominated node in post DOM tree"
      )
      .protoId(182)

    method
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = unknown)

    methodReturn
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    literal
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    callNode
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    identifier
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    fieldIdentifier
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    ret
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    block
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    unknown
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    controlStructure
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    methodRef
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    typeRef
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = methodReturn)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = method)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = unknown)

    jumpTarget
      .addOutEdge(edge = dominate, inNode = callNode)
      .addOutEdge(edge = dominate, inNode = identifier)
      .addOutEdge(edge = dominate, inNode = fieldIdentifier)
      .addOutEdge(edge = dominate, inNode = literal)
      .addOutEdge(edge = dominate, inNode = ret)
      .addOutEdge(edge = dominate, inNode = methodRef)
      .addOutEdge(edge = dominate, inNode = typeRef)
      .addOutEdge(edge = dominate, inNode = block)
      .addOutEdge(edge = dominate, inNode = jumpTarget)
      .addOutEdge(edge = dominate, inNode = controlStructure)
      .addOutEdge(edge = dominate, inNode = unknown)
      .addOutEdge(edge = postDominate, inNode = callNode)
      .addOutEdge(edge = postDominate, inNode = identifier)
      .addOutEdge(edge = postDominate, inNode = fieldIdentifier)
      .addOutEdge(edge = postDominate, inNode = literal)
      .addOutEdge(edge = postDominate, inNode = ret)
      .addOutEdge(edge = postDominate, inNode = methodRef)
      .addOutEdge(edge = postDominate, inNode = typeRef)
      .addOutEdge(edge = postDominate, inNode = block)
      .addOutEdge(edge = postDominate, inNode = jumpTarget)
      .addOutEdge(edge = postDominate, inNode = controlStructure)
      .addOutEdge(edge = postDominate, inNode = unknown)

  }

}