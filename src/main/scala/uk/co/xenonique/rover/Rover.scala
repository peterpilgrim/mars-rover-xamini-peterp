package uk.co.xenonique.rover

import uk.co.xenonique.rover.Direction.{Direction, EAST, NORTH, SOUTH, WEST}

import scala.util.control.Breaks.{break, breakable}

class Rover(private val grid: Grid = Grid()) {
  private var x = 0
  private var y = 0
  private var direction: Direction = NORTH

  def execute(commands: String): String = {
    var blockages = ""
    breakable {
      for (command <- commands) {
        val (lastX, lastY) = (x, y)
        command match {
          case 'L' => rotateLeft()
          case 'R' => rotateRight()
          case 'M' =>
            move()
            if (grid.isObstructed(x, y)) {
              x = lastX
              y = lastY
              blockages = "O:"
              break
            }
          case _ => throw new IllegalArgumentException(s"unknown command: [$command]")
        }
      }
    }

    s"$blockages$x:$y:${direction.shortName}"
  }

  def rotateRight(): Unit = {
    direction match {
      case NORTH => direction = EAST
      case EAST => direction = SOUTH
      case SOUTH => direction = WEST
      case WEST => direction = NORTH
      case _ => throw new IllegalStateException(s"turn right - unknown direction: [$direction]")
    }
  }

  def rotateLeft(): Unit = {
    direction match {
      case NORTH => direction = WEST
      case EAST => direction = NORTH
      case SOUTH => direction = EAST
      case WEST => direction = SOUTH
      case _ => throw new IllegalStateException(s"turn left - unknown direction: [$direction]")
    }
  }

  def move(): Unit = {
    direction match {
      case NORTH =>
        y = y + 1
        if (y >= grid.rows)
          y = 0

      case EAST =>
        x = x + 1
        if (x >= grid.cols)
          x = 0

      case WEST =>
        x = x - 1
        if (x < 0)
          x = grid.cols - 1

      case SOUTH =>
        y = y - 1
        if (y < 0)
          y = grid.rows - 1

      case _ => throw new IllegalStateException(s"move forward - unknown direction: [$direction]")
    }
  }

}
