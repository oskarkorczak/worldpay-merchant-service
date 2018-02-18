package core.domain.service

import core.Repository
import core.domain.offer.Offer

class SearchService(repository: Repository) {

  def search(phrase: String): Map[String, Offer] = {
    repository.findByDesc(phrase)
  }
}
