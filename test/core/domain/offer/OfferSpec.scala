package core.domain.offer

import java.time.Instant
import java.time.temporal.ChronoUnit.DAYS

import core.domain.Currency.GBP
import core.domain.id.FixedIdGenerator.fixedId
import core.domain.time.FixedTimeGenerator.fixedTime
import org.scalatest.{FlatSpec, Matchers}

class OfferSpec extends FlatSpec with Matchers {

  val now = Instant.now()
  val validOfferInstant = now.plus(3, DAYS)
  val offer = Offer(fixedId, "description", 23.45, GBP, validOfferInstant)

  "Offer" must "be valid, when it is not expired" in {
    offer.isValid() should be (true)
  }

  "Offer" must "be invalid, when it is expired" in {
    val invalidOffer = validOfferInstant.minus(6, DAYS)

    val offer = Offer(fixedId, "description", 23.45, GBP, invalidOffer)

    offer.isValid() should be (false)
  }

  "Offer" must "be invalid, when it is cancelled" in {
    val cancelledOffer = Offer.cancel(offer)

    cancelledOffer.isValid() should be (false)
  }

  "Cancelled offer" must "have correct properties" in {
    val cancelledOffer = Offer.cancel(offer)

    cancelledOffer should have (
      'id (offer.id),
      'desc (offer.desc),
      'price (offer.price),
      'currency (offer.currency),
      'expiryTime (offer.expiryTime),
      'cancelled (true)
    )
  }

  "Offer" must "return formatted expiry date" in {
    val offer = Offer(fixedId, "description", 23.45, GBP, fixedTime)

    offer.formattedExpiryTime() should be ("01/01/70 01:00")
  }

}
