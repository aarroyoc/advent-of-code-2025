package day6

import scala.io.Source
import scala.collection.mutable.ArrayBuffer

case class WorkSheet(nums: Array[Array[Long]], ops: Array[String]) {
  def opsOne: Array[Long] = {
    nums
      .transpose
      .zip(ops)
      .map { (ns, op) =>
        op match {
          case "+" => ns.reduce(_ + _)
          case "*" => ns.reduce(_ * _)
        }
      }
  }

  def opsTwo: Array[Long] = {
    nums
      .zip(ops)
      .map { (ns, op) =>
        op match {
          case "+" => ns.reduce(_ + _)
          case "*" => ns.reduce(_ * _)
        }
      }
  }
}


object Day6 {
  def main: Unit = {
    println("Day 6")
    val testP1 = partOne("data/day6.sample")
    val testP2 = partTwo("data/day6.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = partOne("data/day6.input")
    val p2 = partTwo("data/day6.input")
    println(s"Input file: $p1 / $p2")
  }

  def partOne(input: String): Long = {
    val worksheet = parse(input)
    worksheet.opsOne.sum
  }

  def partTwo(input: String): Long = {
    val worksheet = parseTwo(input)
    worksheet.opsTwo.sum
  }

  def parse(input: String): WorkSheet = {
    val fileContents = Source.fromFile(input)

    val nLines = fileContents.getLines.size

    val nums = fileContents.reset.getLines.take(nLines - 1).map { line =>
      line.split(" ").filter(_ != "").map(_.toLong).toArray
    }.toArray
    val ops = fileContents.reset.getLines.drop(nLines - 1).map { line =>
      line.split(" ").filter(_ != "").toArray
    }.toArray.head

    WorkSheet(nums, ops)
  }

  def parseTwo(input: String): WorkSheet = {
    val fileContents = Source.fromFile(input).getLines.toArray

    val nLines = fileContents.size
    val numsLines = fileContents.take(nLines - 1)
    val ops = fileContents(nLines - 1).split(" ").filter(_ != "").toArray

    val worksheet = ArrayBuffer.empty[Array[Long]]
    var nums = ArrayBuffer.empty[Long]

    for(i <- 0 until fileContents(0).size) {
      val numStr = numsLines.map(_(i)).mkString.trim
      if(numStr == "") {
        worksheet += nums.toArray
        nums = ArrayBuffer.empty[Long]
      } else {
        nums += numStr.toLong
      }
    }
    worksheet += nums.toArray
    WorkSheet(worksheet.toArray, ops)
  }
}
