package core.domain.time

import java.time.temporal.ChronoUnit
import java.util.NoSuchElementException

import org.scalatest.{FlatSpec, Matchers}

class TimeUnit$Spec extends FlatSpec with Matchers {

  it must "convert time unit to correct enum value" in {
    TimeUnit.convert(TimeUnit.DAY.toString) should be (ChronoUnit.DAYS)
  }

  it must "throw correct exception, when wrong time unit" in {
    val thrown = intercept[NoSuchElementException] {
      TimeUnit.convert("incorrect time unit")
    }
    assert(thrown.getMessage === "No value found for 'incorrect time unit'")
  }

}
