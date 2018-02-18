package core.domain.time

import java.time.Instant

import core.domain.time.FixedTimeGenerator.fixedTime

class FixedTimeGenerator() extends TimeGenerator {

  def now(): Instant = {
    fixedTime
  }
}

object FixedTimeGenerator {
  val fixedTime = Instant.EPOCH
}