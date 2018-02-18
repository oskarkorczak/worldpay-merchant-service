package infra.model

import org.scalatest.{FlatSpec, Matchers}

class OfferModelSpec extends FlatSpec with Matchers {

  it should "correctly initialize object" in {
    val description = "desc"
    val price = 23.00
    val ccy = "GBP"
    val validFor = 3
    val timeUnit = "DAY"

    val offerModel = OfferModel(description, price, ccy, validFor, timeUnit)

    offerModel should have (
      'description (description),
      'price (price),
      'currency (ccy),
      'validFor (validFor),
      'timeUnit (timeUnit)
    )
  }
}