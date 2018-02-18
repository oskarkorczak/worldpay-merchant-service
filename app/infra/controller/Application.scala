package controllers

import core.domain.Currency
import core.domain.offer.{Offer, OfferConverter}
import core.domain.service.SearchService
import core.domain.time.TimeUnit
import infra.cache.CacheBasedRepository
import infra.model
import infra.model.SearchPhrase
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._


class Application(components: ControllerComponents, repository: CacheBasedRepository,
                  searchService: SearchService, offerConverter: OfferConverter) extends AbstractController(components) {

  def index = Action {
    val allOffers = repository.findAll()
    Ok(infra.views.html.index(allOffers))
  }

  def offers = Action {
    val currencies: core.domain.Currency.ValueSet = Currency.values
    val timeUnits: TimeUnit.ValueSet = TimeUnit.values
    Ok(infra.views.html.addOffer(None, currencies, timeUnits))
  }

  def addOffer = Action { implicit request =>
    userOfferForm.bindFromRequest().fold(
      formWithErrors => {
        val currencies: core.domain.Currency.ValueSet = Currency.values
        val timeUnits: TimeUnit.ValueSet = TimeUnit.values
        Ok(infra.views.html.addOffer(Some(formWithErrors), currencies, timeUnits))
      },
      userOffer => {
        val offer: Offer = offerConverter.convert(userOffer)
        repository.add(offer)
        Redirect("/")
      }
    )
  }

  def cancel(id: String) = Action {
    val offerOption = repository.findById(id)

    offerOption match {
      case Some(offerMapEntry) => {
        val cancelledOffer = Offer.cancel(offerMapEntry._2)
        repository.add(cancelledOffer)
        Redirect("/")
      }
      case None => Results.Ok("Cancellation went wrong.")
    }
  }

  def search() = Action { implicit request =>
      searchPhraseForm.bindFromRequest().fold(
        formWithErrors => {
          Redirect("/")
        },
        searchedPhrase => {
          val filteredOffers = searchService.search(searchedPhrase.search)
          if(filteredOffers.nonEmpty) {
            Ok(infra.views.html.showFilteredOffers(None, filteredOffers))
          } else {
            Ok(infra.views.html.showFilteredOffers(Some("No offers found. Please try again."), Map[String, Offer]()))
          }
        }
      )
  }

  val userOfferForm = Form {
    val emptyField = ""
    mapping(
      "description" -> nonEmptyText,
      "price" -> bigDecimal(8, 2),
      "currency" -> nonEmptyText.verifying("Currency cannot be empty.", ccy => !ccy.equals(emptyField)),
      "valid" -> number(min = 1, max = Int.MaxValue),
      "timeUnit" -> nonEmptyText.verifying("Time unit cannot be empty.", timeUnit => !timeUnit.equals(emptyField))
    )(model.OfferModel.apply)(model.OfferModel.unapply)
  }

  val searchPhraseForm = Form {
    mapping(
      "search" -> nonEmptyText
    )(SearchPhrase.apply)(SearchPhrase.unapply)
  }
}
