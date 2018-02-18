package core.domain.id

import java.util.UUID

class UniqueIdGenerator extends IdGenerator {

  def generate(): String = {
    UUID.randomUUID().toString
  }
}
