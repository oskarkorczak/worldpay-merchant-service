package core.domain.offer

import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle.SHORT
import java.time.{Instant, ZoneId}
import java.util.Locale.UK

import core.domain.Currency.Currency

case class Offer(id: String, desc: String, price: BigDecimal, currency: Currency, expiryTime: Instant, cancelled: Boolean = false) {

  def formattedExpiryTime(): String = {
    Offer.formatter.format(expiryTime)
  }

  def isValid(): Boolean = {
    expiryTime.isAfter(Instant.now()) && !cancelled
  }
}

object Offer {

  val formatter = DateTimeFormatter
      .ofLocalizedDateTime(SHORT)
      .withLocale(UK)
      .withZone(ZoneId.systemDefault())

  def cancel(o: Offer): Offer = {
    Offer(o.id, o.desc, o.price, o.currency, o.expiryTime, cancelled = true)
  }
}
