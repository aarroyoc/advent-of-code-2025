package day3

import scala.io.Source

object Day3 {
  def main: Unit = {
    println("Day 3")
    val testP1 = partOne("data/day3.sample")
    val testP2 = partTwo("data/day3.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = partOne("data/day3.input")
    val p2 = partTwo("data/day3.input")
    println(s"Input file: $p1 / $p2")
  }

  def partOne(input: String): Long = {
    val banks = parse(input)
    banks.map { bank =>
      val firstDigit = bank
        .slice(0, bank.size - 1)
        .reduce(math.max)
      val firstDigitPos = bank.indexOf(firstDigit)
      val secondDigit = bank
        .slice(firstDigitPos + 1, bank.size)
        .reduce(math.max)
      firstDigit * 10 + secondDigit
    }.sum
  }

  def partTwo(input: String): Long = {
    val banks = parse(input)
    banks.map { bank =>
      var consumed = 0
      var n = 0L
      for(d <- 1 to 12) {
        val maxDigit = bank
          .slice(consumed, bank.size - (12 - d))
          .reduce(math.max)
        consumed = bank.indexOf(maxDigit, consumed) + 1
        n *= 10
        n += maxDigit
      }
      n
    }.sum
  }

  def parse(input: String): Seq[Seq[Int]] = {
    val fileContents = Source.fromFile(input).getLines

    fileContents.map { line =>
      line.map(_.toInt - 48)
    }.toSeq
  }
}
