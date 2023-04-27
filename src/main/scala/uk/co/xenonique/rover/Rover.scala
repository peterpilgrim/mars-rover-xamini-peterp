package uk.co.xenonique.rover

class Rover {
  private var x = 0
  private var y = 0
  private var direction: String = "N"

  def execute(commands: String): String = {
    for (command <- commands) {
      command match {
        case 'L' => rotateLeft(command)
        case 'R' => rotateRight(command)
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
      case _ => throw new IllegalStateException(s"move right - unknown direction: [$direction]")
    }
  }

  def rotateLeft(command: Char): Unit = {
    direction match {
      case "N" => direction = "W"
      case "E" => direction = "N"
      case "S" => direction = "E"
      case "W" => direction = "S"
      case _ => throw new IllegalStateException(s"move right - unknown direction: [$direction]")
    }
  }

}
