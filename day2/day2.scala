package day2

import scala.io.Source
import scala.collection.immutable.NumericRange
import scala.util.boundary, boundary.break
import scala.collection.immutable.ArraySeq

object Day2 {
  def main: Unit = {
    println("Day 2")
    val testP1 = Day2.partOne("data/day2.sample")
    val testP2 = Day2.partTwo("data/day2.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = Day2.partOne("data/day2.input")
    val p2 = Day2.partTwo("data/day2.input")
    println(s"Input file: $p1 / $p2")
  }

  def partOne(input: String): BigInt = {
    val ranges = parse(input)
    ranges.flatMap { range =>
      range.filter(twiceAppearance)
    }.sum
  }

  def partTwo(input: String): BigInt = {
    val ranges = parse(input)
    ranges.flatMap { range =>
      range.filter(multiAppearance)
    }.sum
  }

  private def multiAppearance(n: BigInt): Boolean = boundary {
    val nStr = n.toString
    for(i <- 1 to nStr.size / 2) {
      val pattern = nStr.splitAt(i)(0)
      if(pattern * (nStr.size / pattern.size) == nStr) {
        break(true)
      }
    }
    false
  }

  private def twiceAppearance(n: BigInt): Boolean = {
    val nStr = n.toString
    nStr.size % 2 == 0 && nStr.splitAt(nStr.size / 2)(0) * 2 == nStr 
  }

  private def parse(input: String): Seq[NumericRange.Inclusive[BigInt]] = {
    val fileContents = Source.fromFile(input).mkString.trim
    ArraySeq.unsafeWrapArray(fileContents.split(",")).map { range =>
      val r = range.split("-")
      BigInt(r(0)) to BigInt(r(1))
    }
  }
}
