package infra.model

import org.scalatest.{FlatSpec, Matchers}

class SearchPhraseSpec extends FlatSpec with Matchers {

  it should "correctly initialize object" in {
    val phrase = "a phrase"

    val searchPhrase = SearchPhrase(phrase)

    searchPhrase.search should be (phrase)
  }
}
