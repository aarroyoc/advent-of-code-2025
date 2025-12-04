package day4

import scala.io.Source

case class World(grid: Vector[Boolean], width: Int, height: Int) {
  def get(x: Int, y: Int): Boolean = {
    if (x < 0 || y < 0 || x >= width || y >= height) {
      false
    } else {
      grid(y * width + x)
    }
  }

  def numberOfRolls: Int = grid.count(_ == true)

  def forkliftAccess(x: Int, y: Int): Boolean = {
    val checks = List(
      (x - 1, y - 1),
      (x    , y - 1),
      (x + 1, y - 1),
      (x - 1, y    ),
      (x + 1, y    ),
      (x - 1, y + 1),
      (x    , y + 1),
      (x + 1, y + 1),
    )
    checks.count((x,y) => get(x,y)) < 4
  }

  def accesses: Int = {
    val accesses = for {
      x <- 0 until width
      y <- 0 until height
      if get(x, y)
    } yield forkliftAccess(x, y)
    accesses.count(_ == true)
  }

  def removeRolls: World = {
    val newGrid = for {
      x <- 0 until width
      y <- 0 until height
    } yield if (get(x,y)) {
      !forkliftAccess(x, y)
    } else {
      false
    }
    World(newGrid.toVector, width, height)
  }
}

object Day4 {

  def main: Unit = {
    println("Day 4")
    val testP1 = partOne("data/day4.sample")
    val testP2 = partTwo("data/day4.sample")
    println(s"Sample file: $testP1 / $testP2")
    val p1 = partOne("data/day4.input")
    val p2 = partTwo("data/day4.input")
    println(s"Input file: $p1 / $p2")
  }

  def partOne(input: String): Int = {
    val world = parse(input)
    world.accesses
  }

  def partTwo(input: String): Int = {
    var world = parse(input)
    val initialWorldRolls = world.numberOfRolls
    while(world.accesses > 0) {
      world = world.removeRolls
    }
    initialWorldRolls - world.numberOfRolls
  }

  private def parse(input: String): World = {
    val fileContents = Source.fromFile(input).getLines
    
    var height = 0
    val grid = fileContents.flatMap { line =>
      height += 1
      line.map(_ == '@')
    }.toVector
    val width = grid.size / height
    World(grid, width, height)
  }
}
