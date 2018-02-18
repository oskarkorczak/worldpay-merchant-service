package core.domain.id

import core.domain.id.FixedIdGenerator.fixedId

class FixedIdGenerator extends IdGenerator {

  def generate(): String = {
    fixedId
  }
}

object FixedIdGenerator {
  val fixedId = "fixed-id"
}
