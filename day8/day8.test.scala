package day8

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day8Test extends AnyFlatSpec {

  behavior of "input data"

  it should "return correct part one" in {
    assertResult(175440) {
      Day8.partOne("data/day8.input")
    }
  }

  it should "return correct part two" in {
    assertResult(3200955921L) {
      Day8.partTwo("data/day8.input")
    }
  }
}
