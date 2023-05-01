package uk.co.xenonique.rover

object Direction extends Enumeration {
  protected case class DirectionVal(shortName: String) extends super.Val

  import scala.language.implicitConversions

  implicit def valueToDirectionVal(x: Value): DirectionVal = x.asInstanceOf[DirectionVal]

  def shortNameToDirection(str1: String): Direction = {

    val y = Direction.values.find( _.shortName.equals(str1) )
    println(">>>> y="+y)
    if (y.isDefined) y.get else null
  }

  type Direction = Value

  val NORTH: DirectionVal = DirectionVal("N")
  val EAST: DirectionVal = DirectionVal("E")
  val SOUTH: DirectionVal = DirectionVal("S")
  val WEST: DirectionVal = DirectionVal("W")
}
