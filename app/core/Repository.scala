package core

import core.domain.offer.Offer

trait Repository {

  def add(offer: Offer): Unit

  def findAll(): Map[String, Offer]

  def findById(id: String): Option[(String, Offer)]

  def findByDesc(phrase: String): Map[String, Offer]

}
