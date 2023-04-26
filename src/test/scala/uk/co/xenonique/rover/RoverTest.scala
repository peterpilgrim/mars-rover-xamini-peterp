package uk.co.xenonique.rover

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._

class RoverTests extends AnyFunSuite with BeforeAndAfterEach {

  var rover: Rover = _

  override def beforeEach() {
    rover = new Rover();
  }

  test("move the rover") {
    rover.execute("R") should be("E")
  }


  test("move the rover again") {
    rover.execute("RR") should be("S")
  }
}
