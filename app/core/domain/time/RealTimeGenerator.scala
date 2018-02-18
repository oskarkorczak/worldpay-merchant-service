package core.domain.time

import java.time.Instant

class RealTimeGenerator extends TimeGenerator {

  def now(): Instant = {
    Instant.now()
  }
}
