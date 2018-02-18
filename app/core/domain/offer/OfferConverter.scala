package core.domain.offer

import java.time.Instant

import core.domain.Currency
import core.domain.id.IdGenerator
import core.domain.time.{TimeGenerator, TimeUnit}
import infra.model.OfferModel

class OfferConverter(idGenerator: IdGenerator, timeGenerator: TimeGenerator) {

  def convert(userOffer: OfferModel): Offer = {
    val currency: Currency.Value = Currency.withName(userOffer.currency)
    val now = timeGenerator.now()
    val expiryTime: Instant = now.plus(userOffer.validFor, TimeUnit.convert(userOffer.timeUnit))

    Offer(idGenerator.generate(), userOffer.description, userOffer.price, currency, expiryTime)
  }

}
