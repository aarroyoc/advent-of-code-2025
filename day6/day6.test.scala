package day6

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day6Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct part one" in {
    assertResult(4277556) {
      Day6.partOne("data/day6.sample")
    }
  }

  it should "return correct part two" in {
    assertResult(3263827) {
      Day6.partTwo("data/day6.sample")
    }
  }

  behavior of "input data"

  it should "return correct part one" in {
    assertResult(5335495999141L) {
      Day6.partOne("data/day6.input")
    }
  }

  it should "return correct part two" in {
    assertResult(10142723156431L) {
      Day6.partTwo("data/day6.input")
    }
  }
}
