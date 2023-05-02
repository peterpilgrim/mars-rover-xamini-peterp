package uk.co.xenonique.rover

case class Obstacle( x: Int, y: Int )

case class Grid( rows: Int = 10, cols: Int = 10,
            obstacles: Set[Obstacle] = Set.empty ) {

  def isObstructed( x: Int, y: Int): Boolean = {
    obstacles.contains(Obstacle(x,y))
  }
}
