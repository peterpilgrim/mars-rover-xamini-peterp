package uk.co.xenonique.rover

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.prop.TableDrivenPropertyChecks._

class RoverTests extends AnyFunSuite with BeforeAndAfterEach with TableDrivenPropertyChecks {

  var rover: Rover = _

  val ROTATE_RIGHT_COMMANDS = Table(
    ("R", "E"),
    ("RR", "S"),
    ("RRR", "W"),
    ("RRRR", "N"),
    ("RRRRR", "E"),
  )


  override def beforeEach() {
    rover = new Rover();
  }

  test("move the rover") {
    rover.execute("R") should be("E")
  }


  test("move the rover again") {
    rover.execute("RR") should be("S")
  }

  forAll(ROTATE_RIGHT_COMMANDS) {
    (commands: String, position: String) =>
      test( s"move rover with input: [${commands}] should result in final position: [$position]") {
        rover.execute(commands) should be (position)
      }

  }

}

