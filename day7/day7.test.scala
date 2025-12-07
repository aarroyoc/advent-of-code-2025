package day7

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day7Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct part one" in {
    assertResult(21) {
      Day7.partOne("data/day7.sample")
    }
  }

  it should "return correct part two" in {
    assertResult(40) {
      Day7.partTwo("data/day7.sample")
    }
  }

  behavior of "input data"

  it should "return correct part one" in {
    assertResult(1690) {
      Day7.partOne("data/day7.input")
    }
  }

  it should "return correct part two" in {
    assertResult(221371496188107L) {
      Day7.partTwo("data/day7.input")
    }
  }
}
