package day7

import scala.io.Source
import scala.collection.mutable.Queue

case class World(startPos: Int, splitters: Seq[Seq[Int]], width: Int)

object Day7 {
  def main: Unit = {
    println("Day 7")
    val testP1 = partOne("data/day7.sample")
    val testP2 = partTwo("data/day7.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = partOne("data/day7.input")
    val p2 = partTwo("data/day7.input")
    println(s"Input file: $p1 / $p2")
  }

  def partOne(input: String): Int = {
    val world = parse(input)
    val tachyons = Set(world.startPos)
    var splits = 0 
    world.splitters.foldLeft(tachyons) { (tachyons, worldLine) =>
      tachyons.flatMap { tachyon =>
        if (worldLine.contains(tachyon)) {
          splits += 1
          Set(tachyon - 1, tachyon + 1)
        } else {
          Set(tachyon)
        }
      }
    }
    splits
  }

  def partTwo(input: String): Long = {
    val world = parse(input)
    //var branches = 0
    //val explorations = Queue((0, world.startPos))
    //while (explorations.size > 0) {
    //  val (floor, pos) = explorations.dequeue
    //  if (floor + 1 == world.splitters.size) {
    //    branches += 1
    //  } else {
    //    if (world.splitters(floor + 1).contains(pos)) {
    //      explorations ++= List((floor + 1, pos - 1), (floor + 1, pos + 1))
    //    } else {
    //      explorations ++= List((floor + 1, pos))
    //    }
    //  }
    //}
    //branches

    val timelines = Array.fill(world.width)(0L)
    timelines(world.startPos) = 1
    for(worldLine <- world.splitters) {
      for(splitter <- worldLine) {
        timelines(splitter - 1) += timelines(splitter)
        timelines(splitter + 1) += timelines(splitter)
        timelines(splitter) = 0L
      }
    }
    timelines.sum
  }

  def parse(input: String): World = {
    val fileContents = Source.fromFile(input).getLines.toArray
    val width = fileContents(0).size
    val startPos = fileContents(0).indexOf("S")

    val splitters = fileContents.drop(1).map { line =>
      line.zipWithIndex.filter((c, i) => c == '^').map((c, i) => i)
    }.toIndexedSeq
    World(startPos, splitters, width)
  }
}
