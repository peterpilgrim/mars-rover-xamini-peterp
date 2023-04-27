package uk.co.xenonique.rover

class Rover( private val grid: Grid  = new Grid()) {
  private var x = 0
  private var y = 0
  private var direction: String = "N"

  def execute(commands: String): String = {
    for (command <- commands) {
      command match {
        case 'L' => rotateLeft(command)
        case 'R' => rotateRight(command)
        case 'M' => move(command)
        case _ => throw new IllegalArgumentException(s"unknown command: [$command]")
      }
    }
    s"$x:$y:$direction"
  }

  def rotateRight(command: Char): Unit = {
    direction match {
      case "N" => direction = "E"
      case "E" => direction = "S"
      case "S" => direction = "W"
      case "W" => direction = "N"
      case _ => throw new IllegalStateException(s"turn right - unknown direction: [$direction]")
    }
  }

  def rotateLeft(command: Char): Unit = {
    direction match {
      case "N" => direction = "W"
      case "E" => direction = "N"
      case "S" => direction = "E"
      case "W" => direction = "S"
      case _ => throw new IllegalStateException(s"turn left - unknown direction: [$direction]")
    }
  }

  def move(command: Char): Unit = {
    direction match {
      case "N" =>
        y = y + 1
        if ( y >= grid.rows )
          y = 0

      case "E" =>
        x = x + 1
        if (x >= grid.cols)
          x = 0

      case "W" =>
        x = x - 1
        if (x < 0)
          x = grid.cols - 1

      case "S" =>
        y = y - 1
        if (y < 0)
          y = grid.rows - 1

      case _ => throw new IllegalStateException(s"move forward - unknown direction: [$direction]")
    }

  }


}
