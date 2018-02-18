package infra.model

case class OfferModel(description: String,
                      price: BigDecimal,
                      currency: String,
                      validFor: Int,
                      timeUnit: String
                     )
