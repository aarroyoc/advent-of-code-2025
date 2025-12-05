package day5

import scala.io.Source
import scala.collection.mutable.ListBuffer

case class Range(from: Long, to: Long) {
  def contains(x: Long): Boolean =
    x >= from && x <= to
}

case class Inventory(
  freshRanges: List[Range],
  items: List[Long]
) {
  def countFreshItems: Long = {
    items.count { item =>
      freshRanges.exists(_.contains(item))
    }
  }

  def freshRangeSize: Long = {
    0
  }
}

object Day5 {

  def partOne(input: String): Long = {
    val inventory = parse(input)
    inventory.countFreshItems
  }

  def partTwo(input: String): Long = {
    val inventory = parse(input)
    inventory.freshRangeSize
  }

  def parse(input: String): Inventory = {
    val fileContents = Source.fromFile(input).getLines

    val freshRanges = ListBuffer.empty[Range]
    val items = ListBuffer.empty[Long]

    var secondPart = false
    for(line <- fileContents) {
      if(!secondPart) {
        if(line == "") {
          secondPart = true
        } else {
          val p = line.split("-")
          freshRanges.addOne(Range(p(0).toLong, p(1).toLong))
        }
      } else {
        items.addOne(line.toLong)
      }
    }
    Inventory(freshRanges.toList, items.toList)
  }
}
