package core.domain.time

import java.time.Instant

trait TimeGenerator {

  def now(): Instant

}
