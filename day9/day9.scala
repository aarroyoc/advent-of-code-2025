package day9

import scala.io.Source

case class Point(x: Long, y: Long)

object Day9 {
  def partOne(input: String): Long = {
    val points = parse(input)
    val areas = for {
      p1 <- points
      p2 <- points
      if p1 != p2
    } yield area(p1, p2)
    areas.max
  }

  // 140783340 too low
  def partTwo(input: String): Long = {
    val points = parse(input)
    val areas = for {
      p1 <- points
      p2 <- points
      if p1.x < p2.x && p1.y < p2.y
      if p1 != p2
      if anotherCornerIsAlsoGreen(p1, p2, points)
      if free(p1, p2, points)
    } yield area(p1, p2)
    areas.max
  }

  // not true
  def anotherCornerIsAlsoGreen(p1: Point, p2: Point, points: Seq[Point]): Boolean = {
    val p3 = Point(p1.x, p2.y)
    val p4 = Point(p2.x, p1.y)
    isInGreenZone(p3, points) && isInGreenZone(p4, points)
  }

  def isInGreenZone(p: Point, points: Seq[Point]): Boolean = {
    points.contains(p) ||
    (points.exists(px => (px.x == p.x && px.y > p.y)) && points.exists(px => (px.x == p.x && px.y < p.y))) ||
    (points.exists(px => (px.y == p.y && px.x > p.x)) && points.exists(px => (px.y == p.y && px.x < p.x)))
  }

  def free(p1: Point, p2: Point, points: Seq[Point]): Boolean = {
    // check there are no points inside the area of p1 and p2
    !points.exists { p =>
      val maxX = math.max(p1.x, p2.x)
      val minX = math.min(p1.x, p2.x)
      val maxY = math.max(p1.y, p2.y)
      val minY = math.min(p1.y, p2.y)
      minX < p.x && p.x < maxX &&
      minY < p.y && p.y < maxY
    }
  }

  def area(p1: Point, p2: Point): Long = {
    val x = math.abs(p1.x - p2.x) + 1
    val y = math.abs(p1.y - p2.y) + 1
    x * y
  }

  def parse(input: String): Seq[Point] = {
    val fileContents = Source.fromFile(input).getLines

    fileContents.map { line =>
      val m = line.split(",")
      Point(
        x = m(0).toLong,
        y = m(1).toLong,
      )
    }.toSeq
  }
}

