package day5

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day5Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct part one" in {
    assertResult(3) {
      Day5.partOne("data/day5.sample")
    }
  }

  it should "return correct part two" in {
    assertResult(14) {
      Day5.partTwo("data/day5.sample")
    }
  }

  behavior of "input data"

  it should "return correct part one" in {
    assertResult(615) {
      Day5.partOne("data/day5.input")
    }
  }

  it should "return correct part two" in {
    assertResult(353716783056994L) {
      Day5.partTwo("data/day5.input")
    }
  }
}
