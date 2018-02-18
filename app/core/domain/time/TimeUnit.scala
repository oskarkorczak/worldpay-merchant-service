package core.domain.time

import java.time.temporal.ChronoUnit

object TimeUnit extends Enumeration {
  type TimeUnit = Value
  val MINUTE, DAY = Value

  def convert(timeUnitStr: String): ChronoUnit = {
    val timeUnit = TimeUnit.withName(timeUnitStr)
    timeUnit match {
      case MINUTE => ChronoUnit.MINUTES
      case DAY => ChronoUnit.DAYS
    }
  }
}
