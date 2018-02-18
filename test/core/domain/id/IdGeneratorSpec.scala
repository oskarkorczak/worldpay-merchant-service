package core.domain.id

import org.scalatest.{FlatSpec, Matchers}

class IdGeneratorSpec extends FlatSpec with Matchers {

  val idGen = new UniqueIdGenerator()

  "Id generator" must "generate UUID type" in {
    val id = idGen.generate()

    id shouldBe a [String]
  }

  "Id generator" must "always generate different ids" in {
    val firstId = idGen.generate()
    val secondId = idGen.generate()

    firstId should not be secondId
  }

}
