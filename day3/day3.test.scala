package day3

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day3Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct part one" in {
    assertResult(357) {
      Day3.partOne("data/day3.sample")
    }
  }

  it should "return correct part two" in {
    assertResult(3121910778619L) {
      Day3.partTwo("data/day3.sample")
    }
  }

  behavior of "input data"

  it should "return correct part one" in {
    assertResult(17263) {
      Day3.partOne("data/day3.input")
    }
  }

  it should "return correct part two" in {
    assertResult(170731717900423L) {
      Day3.partTwo("data/day3.input")
    }
  }
}
