package infra.cache

import core.Repository
import core.domain.offer.Offer

class CacheBasedRepository(cache: collection.mutable.Map[String, Offer]) extends Repository {

  def add(offer: Offer): Unit = {
    cache.put(offer.id, offer)
  }

  def findAll(): Map[String, Offer] = {
    cache.toMap
  }

  def findById(id: String): Option[(String, Offer)] = {
    cache.find(o => o._1.toString == id)
  }

  def findByDesc(phrase: String): Map[String, Offer] = {
    cache.filter(offer => offer._2.desc.contains(phrase)).toMap
  }
}
