package uk.co.xenonique.rover

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.prop.TableDrivenPropertyChecks._

class RoverTests extends AnyFunSuite with BeforeAndAfterEach with TableDrivenPropertyChecks {

  private var rover: Rover = _

  private val ROTATE_RIGHT_COMMANDS = Table(
    ("commands", "facing position"), // First tuple defines column names
    ("R", "0:0:E"),
    ("RR", "0:0:S"),
    ("RRR", "0:0:W"),
    ("RRRR", "0:0:N"),
    ("RRRRR", "0:0:E"),
  )

  private val ROTATE_LEFT_COMMANDS = Table(
    ("commands", "facing position"), // First tuple defines column names
    ("L", "0:0:W"),
    ("LL", "0:0:S"),
    ("LLL", "0:0:E"),
    ("LLLL", "0:0:N"),
    ("LLLLL", "0:0:W"),
  )

  private val MOVE_COMMANDS = Table(
    ("commands", "facing position"), // First tuple defines column names
    ("M", "0:1:N"),
    ("MM", "0:2:N"),
    ("MMM", "0:3:N"),
    ("RM", "1:0:E"),
    ("RMM", "2:0:E"),
    ("RMMM", "3:0:E"),
    ("LM", "9:0:W"),
    ("LMM", "8:0:W"),
    ("LMMM", "7:0:W"),
    ("LLM", "0:9:S"),
    ("RRM", "0:9:S"),
    ("LLMM", "0:8:S"),
    ("RRMM", "0:8:S"),
    ("LLMMM", "0:7:S"),
    ("RRMMM", "0:7:S"),
    /* WRAP AROUND */
    ("MMMMMMMMMM", "0:0:N"),
    ("RMMMMMMMMMM", "0:0:E"),
    ("LMMMMMMMMMM", "0:0:W"),
    ("LLMMMMMMMMMM", "0:0:S"),
    ("RRMMMMMMMMMM", "0:0:S"),
  )

  private val MOVE_OBSTACLE_COMMANDS = Table(
    ("commands", "facing position"), // First tuple defines column names
    ("MMMM", "O:0:2:N"), //     given a grid with an obstacle at (0, 3)
  )

  override def beforeEach(): Unit = {
    rover = new Rover()
  }

  test("demonstrate direction enumeration values") {
    println(Direction.values)
    println(Direction.NORTH)
    println(Direction.NORTH.shortName)
  }

  test("retrieve enumeration values known shortname") {

    val mappings = Map(
      "N" -> Direction.NORTH,
      "E" -> Direction.EAST,
      "S" -> Direction.SOUTH,
      "W" -> Direction.WEST,
    )

    for (elem <- mappings) {
      val direction = Direction.shortNameToDirection(elem._1)
      direction should be(elem._2)
    }
  }

  test("create a grid with obstacles") {
    val grid = Grid( 7,7, Set( Obstacle(3,2)) )

    println( s">>>> $grid" )

    grid.isObstructed(0,2) should be (false)
    grid.isObstructed(3,0) should be (false)
    grid.isObstructed(3,2) should be (true)
  }

  test("retrieve enumeration value with unknown shortname") {
    val direction1 = Direction.shortNameToDirection("U")
    direction1 should be (null)
  }

  test("move the rover") {
    rover.execute("R") should be("0:0:E")
  }


  test("move the rover again") {
    rover.execute("RR") should be("0:0:S")
  }

  forAll(ROTATE_RIGHT_COMMANDS) {
    (commands: String, position: String) =>
      test(s"move rover with input: [$commands] should result in final position: [$position]") {
        rover.execute(commands) should be(position)
      }
  }

  forAll(ROTATE_LEFT_COMMANDS) {
    (commands: String, position: String) =>
      test(s"move rover with input: [$commands] should result in final position: [$position]") {
        rover.execute(commands) should be(position)
      }
  }

  forAll(MOVE_COMMANDS) {
    (commands: String, position: String) =>
      test(s"move rover with input: [$commands] should result in final position: [$position]") {
        rover.execute(commands) should be(position)
      }
  }

  forAll(MOVE_OBSTACLE_COMMANDS) {
    (commands: String, position: String) =>
      test(s"move rover with input: [$commands] should result in final position: [$position]") {
        rover = new Rover( Grid(10, 10, Set( Obstacle(0,3)) ) )
        rover.execute(commands) should be(position)
      }
  }
}

