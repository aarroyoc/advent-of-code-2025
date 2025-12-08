package day8

import scala.io.Source
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Queue

case class Point3D(x: Long, y: Long, z: Long)

object Day8 {

  def main: Unit = {
    println("Day 8")
    val p1 = partOne("data/day8.input")
    val p2 = partTwo("data/day8.input")
    println(s"Input file: $p1 / $p2")
  }

  def partTwo(input: String): Long = {
    val points = parse(input)
    val circuits = ArrayBuffer.empty[ArrayBuffer[Point3D]]

    val pairs = for {
      p1 <- points
      p2 <- points
      if p1 != p2
    } yield (p1, p2)

    val sortedPairs = Queue[(Point3D, Point3D)]()
    sortedPairs ++= pairs.sortBy((a, b) => distance(a, b))

    while(!(circuits.size == 1 && circuits(0).size == 1000))  {
      val (pointA, pointB) = sortedPairs.dequeue
      val circuitA = circuits.find(c => c.contains(pointA))
      val circuitB = circuits.find(c => c.contains(pointB))

      (circuitA, circuitB) match {
        case (None, None) => circuits.addOne(ArrayBuffer(pointA, pointB))
        case (Some(cA), None) => cA.addOne(pointB)
        case (None, Some(cB)) => cB.addOne(pointA)
        case (Some(cA), Some(cB)) => {
          if(cA != cB) {
            cA.addAll(cB)
            circuits.remove(circuits.indexOf(cB))
          }
        }
      }
    }
    val (pointA, pointB) = sortedPairs.dequeue
    pointA.x * pointB.x
  }

  def partOne(input: String): Long = {
    val points = parse(input)
    val circuits = ArrayBuffer.empty[ArrayBuffer[Point3D]]

    val pairs = for {
      p1 <- points
      p2 <- points
      if p1 != p2
    } yield (p1, p2)

    val sortedPairs = pairs.sortBy((a, b) => distance(a, b))

    for(pair <- sortedPairs.take(2000)) {
      val (pointA, pointB) = pair
      val circuitA = circuits.find(c => c.contains(pointA))
      val circuitB = circuits.find(c => c.contains(pointB))

      (circuitA, circuitB) match {
        case (None, None) => circuits.addOne(ArrayBuffer(pointA, pointB))
        case (Some(cA), None) => cA.addOne(pointB)
        case (None, Some(cB)) => cB.addOne(pointA)
        case (Some(cA), Some(cB)) => {
          if(cA != cB) {
            cA.addAll(cB)
            circuits.remove(circuits.indexOf(cB))
          }
        }
      }
    }
    circuits.map(_.size).sorted.reverse.take(3).reduce(_ * _)
  }

  def unconnectedShortestPair(points: List[Point3D], circuits: ArrayBuffer[ArrayBuffer[Point3D]]): (Point3D, Point3D) = {
    val unconnectedPoints = for {
      p1 <- points
      if !circuits.exists(c => c.contains(p1))
      p2 <- points
      if p1 != p2
    } yield (p1, p2)
    unconnectedPoints.sortBy((a, b) => distance(a, b)).head
  }

  def distance(a: Point3D, b: Point3D): Double = {
    val x = math.abs(a.x - b.x)
    val y = math.abs(a.y - b.y)
    val z = math.abs(a.z - b.z)
    math.sqrt(x*x + y*y + z*z)
  }

  def parse(input: String): List[Point3D] = {
    val fileContents = Source.fromFile(input).getLines
    fileContents.map { line =>
      val items = line.split(",")
      Point3D(items(0).toLong, items(1).toLong, items(2).toLong)
    }.toList
  }
}
