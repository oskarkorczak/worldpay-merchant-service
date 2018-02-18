package infra.cache

import core.domain.Currency
import core.domain.id.FixedIdGenerator.fixedId
import core.domain.offer.Offer
import core.domain.time.FixedTimeGenerator.fixedTime
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

class CacheBasedRepositorySpec extends FlatSpec with Matchers with BeforeAndAfterEach {

  val cache = collection.mutable.Map[String, Offer]()
  val repository = new CacheBasedRepository(cache)

  val anOffer = Offer(fixedId, "a desc", 23.00, Currency.GBP, fixedTime)
  val anotherOffer = Offer("another id", "another desc", 3.00, Currency.USD, fixedTime)

  override def beforeEach() {
    cache.clear()
  }

  it should "find offer by id" in {
    repository.add(anOffer)

    val resultOfferOption = repository.findById(anOffer.id)

    resultOfferOption.get._2 should be (anOffer)
  }

  it should "find offer by description" in {
    repository.add(anOffer)

    val resultOffers = repository.findByDesc(anOffer.desc)

    resultOffers.head._2 should be (anOffer)
  }

  it should "find all offers" in {
    repository.add(anOffer)
    repository.add(anotherOffer)

    val resultOffers = repository.findAll()

    resultOffers.size should be (2)
    resultOffers.values should contain (anOffer)
    resultOffers.values should contain (anotherOffer)
  }

}
