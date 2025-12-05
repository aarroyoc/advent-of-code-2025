package day5

import scala.io.Source
import scala.collection.mutable.ListBuffer

case class Range(from: Long, end: Long) {
  def contains(x: Long): Boolean =
    x >= from && x <= end
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
}

object Day5 {

  def main: Unit = {
    println("Day 5")
    val testP1 = partOne("data/day5.sample")
    val testP2 = partTwo("data/day5.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = partOne("data/day5.input")
    val p2 = partTwo("data/day5.input")
    println(s"Input file: $p1 / $p2")
  }

  def partOne(input: String): Long = {
    val inventory = parse(input)
    inventory.countFreshItems
  }

  def partTwo(input: String): Long = {
    val inventory = parse(input)
    var finished = false
    var ranges = inventory.freshRanges
    while (!finished) {
      val oldRanges = ranges
      ranges = simplifyRanges(ranges)
      if (ranges == oldRanges) {
        finished = true
      }
    }
    countRanges(ranges)
  }

  def countRanges(ranges: List[Range]): Long = {
    ranges.map(x => x.end - x.from + 1).sum
  }

  def simplifyRanges(ranges: List[Range]): List[Range] = {
    val set = ListBuffer.empty[Range]
    for(r <- ranges) {
      // range starts in existing range and ends later
      val h = set.indexWhere(x => x.from <= r.from && x.end < r.end && x.end >= r.from)
      if (h > -1) {
        set(h) = Range(set(h).from, r.end)
      } else {
        // range ends in existing range and starts earlier
        val j = set.indexWhere(x => x.from > r.from && r.end <= x.end && x.from <= r.end)
        if (j > -1) {
          set(j) = Range(r.from, set(j).end)
        } else {
          // range is bigger than existing range
          val q = set.indexWhere(x => x.from >= r.from && x.end <= r.end)
          if (q > -1) {
            set(q) = r
          } else {
            // range fits inside existing range
            val f = set.indexWhere(x => x.from <= r.from && x.end >= r.end)
            if (f == -1) {
              // range is new
              set += r
            }
          }
        }
      }
    }
    set.toList
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
