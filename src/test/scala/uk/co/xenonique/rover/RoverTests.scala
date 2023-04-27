package uk.co.xenonique.rover

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}
import org.scalatest.prop.TableDrivenPropertyChecks._

class RoverTests extends AnyFunSuite with BeforeAndAfterEach with TableDrivenPropertyChecks {

  private var rover: Rover = _

  private val ROTATE_RIGHT_COMMANDS = Table(
    ("commands", "facing position"),  // First tuple defines column names
    ("R", "0:0:E"),
    ("RR", "0:0:S"),
    ("RRR", "0:0:W"),
    ("RRRR", "0:0:N"),
    ("RRRRR", "0:0:E"),
  )

  private val ROTATE_LEFT_COMMANDS = Table(
    ("commands", "facing position"),  // First tuple defines column names
    ("L", "0:0:W"),
    ("LL", "0:0:S"),
    ("LLL", "0:0:E"),
    ("LLLL", "0:0:N"),
    ("LLLLL", "0:0:W"),
  )

  override def beforeEach(): Unit = {
    rover = new Rover()
  }

  test("move the rover") {
    rover.execute("R") should be("0:0:E")
  }


  test("move the rover again") {
    rover.execute("RR") should be("0:0:S")
  }

  forAll(ROTATE_RIGHT_COMMANDS) {
    (commands: String, position: String) =>
      test( s"move rover with input: [$commands] should result in final position: [$position]") {
        rover.execute(commands) should be (position)
      }
  }

  forAll(ROTATE_LEFT_COMMANDS) {
    (commands: String, position: String) =>
      test(s"move rover with input: [$commands] should result in final position: [$position]") {
        rover.execute(commands) should be(position)
      }
  }
}

