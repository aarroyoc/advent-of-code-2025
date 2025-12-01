//> using test.dep org.scalatest::scalatest::3.2.19

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.Assertions._

class Day1Test extends AnyFlatSpec {
  behavior of "sample data"

  it should "return correct password one" in {
    assertResult(3) {
      Day1.passwordOne("day1.sample")
    }
  }

  it should "return correct password two" in {
    assertResult(6) {
      Day1.passwordTwo("day1.sample")
    }
  }

  behavior of "input data"

  it should "return correct password one" in {
    assertResult(1158) {
      Day1.passwordOne("day1.input")
    }
  }

  it should "return correct password two" in {
    assertResult(6860) {
      Day1.passwordTwo("day1.input")
    }
  }
}
