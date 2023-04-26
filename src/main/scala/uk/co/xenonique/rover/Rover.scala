package uk.co.xenonique.rover

class Rover {
  private var direction: String = "N";

  def execute( commands: String): String = {
    for (command <- commands) {
      command match {
        case 'R' => rotateRight(command)
        case _ => throw new IllegalArgumentException(s"unknown command: [$command]")
      }
    }
    direction
  }

  def rotateRight(command: Char): Unit = {
    if (command == 'R') {
      direction match {
        case "N" => direction = "E"
        case "E" => direction = "S"
        case "S" => direction = "W"
        case "W" => direction = "N"
        case _ => throw new IllegalStateException( s"move right - unknown direction: [$direction]")
      }
    }
  }
}
