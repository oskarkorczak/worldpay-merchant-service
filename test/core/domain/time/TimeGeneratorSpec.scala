package core.domain.time

import java.time.Instant

import org.scalatest.{FlatSpec, Matchers}

class TimeGeneratorSpec extends FlatSpec with Matchers {

  val timeGen = new RealTimeGenerator()

  "Time generator" must "generate Instant type" in {
    val now = timeGen.now()

    now shouldBe a [Instant]
  }

  "Time generator" must "generate different time for two probes 1ms apart" in {
    val firstTime = timeGen.now()
    Thread.sleep(1)
    val secondTime = timeGen.now()

    firstTime should not be secondTime
  }

}
