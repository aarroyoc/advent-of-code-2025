package day1

import scala.io.Source

object Day1 {

  def main: Unit = {
    val testP1 = Day1.passwordOne("data/day1.sample")
    val testP2 = Day1.passwordTwo("data/day1.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = Day1.passwordOne("data/day1.input")
    val p2 = Day1.passwordTwo("data/day1.input")
    println(s"Input file: $p1 / $p2")
  }

  def passwordOne(file: String): Int = {
    val instructions = parse(file)

    var dial = 50
    var zeros = 0
    for (inst <- instructions) {
      inst match {
        case Left(i) => {
          dial -= i
          while (dial < 0) {
            dial += 100
          }
        }
        case Right(i) => {
          dial += i
          while (dial > 99) {
            dial -= 100
          }
        }
      }
      if (dial == 0) {
        zeros += 1
      }
    }
    zeros
  }

  def passwordTwo(file: String): Int = {
    val instructions = parse(file)

    var dial = 50
    var zeros = 0

    for (inst <- instructions) {
      inst match {
        case Left(i) => {
          dial -= i
          if (dial + i == 0) {
            zeros -= 1
          }
          while (dial < 0) {
            dial += 100
            zeros += 1
          }
          if (dial == 0) {
            zeros += 1
          }
        }
        case Right(i) => {
          dial += i
          while (dial > 99) {
            dial -= 100
            zeros += 1
          }
        }
      }
    }
    zeros
  }

  private def parse(file: String): Seq[Either[Int, Int]] = {
    val fileContents = Source.fromFile(file).getLines()

    fileContents.map { line =>
      if (line.startsWith("R")) {
        Right(line.substring(1).toInt)
      } else {
        Left(line.substring(1).toInt)
      }
    }.toSeq
  }
}
