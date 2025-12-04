package day4

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day4Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct part one" in {
    assertResult(13) {
      Day4.partOne("data/day4.sample")
    }
  }

  it should "return correct part two" in {
    assertResult(43) {
      Day4.partTwo("data/day4.sample")
    }
  }

  behavior of "input data"

  it should "return correct part one" in {
    assertResult(1474) {
      Day4.partOne("data/day4.input")
    }
  }

  it should "return correct part two" in {
    assertResult(8910) {
      Day4.partTwo("data/day4.input")
    }
  }
}
