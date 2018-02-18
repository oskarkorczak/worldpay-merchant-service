package core.domain.offer

import java.time.temporal.ChronoUnit

import core.domain.Currency.GBP
import core.domain.id.FixedIdGenerator
import core.domain.id.FixedIdGenerator.fixedId
import core.domain.time.FixedTimeGenerator
import core.domain.time.FixedTimeGenerator.fixedTime
import core.domain.time.TimeUnit.DAY
import infra.model.OfferModel
import org.scalatest.{FlatSpec, Matchers}

class OfferConverterSpec extends FlatSpec with Matchers {

    val idGen = new FixedIdGenerator()
    val timeGen = new FixedTimeGenerator()
    val offerService = new OfferConverter(idGen, timeGen)

  it must "correctly convert model offer to domain offer" in {
    val validFor = 3
    val offerModel = OfferModel("description", 23.00, GBP.toString, validFor, DAY.toString)

    val offer = offerService.convert(offerModel)

    offer should have(
      'id (fixedId),
      'desc (offerModel.description),
      'price (offerModel.price),
      'currency (GBP),
      'expiryTime (fixedTime.plus(validFor, ChronoUnit.DAYS)),
      'cancelled (false)
    )
  }
}
