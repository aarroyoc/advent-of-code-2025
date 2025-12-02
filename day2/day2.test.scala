package day2

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day2Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct password one" in {
    assertResult(BigInt("1227775554")) {
      Day2.partOne("data/day2.sample")
    }
  }

  it should "return correct password two" in {
    assertResult(BigInt("4174379265")) {
      Day2.partTwo("data/day2.sample")
    }
  }

  behavior of "input data"

  it should "return correct password one" in {
    assertResult(BigInt("40398804950")) {
      Day2.partOne("data/day2.input")
    }
  }

  it should "return correct password two" in {
    assertResult(BigInt("65794984339")) {
      Day2.partTwo("data/day2.input")
    }
  }
}
