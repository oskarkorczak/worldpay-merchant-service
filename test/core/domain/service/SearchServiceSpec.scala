package core.domain.service

import core.Repository
import org.mockito.Mockito.verify
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

class SearchServiceSpec extends FlatSpec with Matchers with MockitoSugar {

  it must "invoke correct repository method" in {
    val repositoryMock = mock[Repository]
    val service = new SearchService(repositoryMock)
    val phrase = "a phrase"

    service.search(phrase)

    verify(repositoryMock).findByDesc(phrase)
  }
}
