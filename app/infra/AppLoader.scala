package infra

import _root_.controllers.{Application, AssetsComponents}
import com.softwaremill.macwire._
import core.domain.id.UniqueIdGenerator
import core.domain.offer.{Offer, OfferConverter}
import core.domain.time.RealTimeGenerator
import core.domain.service.SearchService
import infra.cache.CacheBasedRepository
import play.api.ApplicationLoader.Context
import play.api._
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.mvc._
import play.api.routing.Router
import play.filters.HttpFiltersComponents
import router.Routes

import scala.concurrent.Future

class AppApplicationLoader extends ApplicationLoader {

  def load(context: Context) = {
    LoggerConfigurator(context.environment.classLoader)
      .foreach { cfg => cfg.configure(context.environment) }
    new AppComponents(context).application
  }
}

class AppComponents(context: Context)  extends BuiltInComponentsFromContext(context)
  with AhcWSComponents with AssetsComponents
  with HttpFiltersComponents {

  lazy val offersCache = collection.mutable.Map[String, Offer]()

  override lazy val controllerComponents = wire[DefaultControllerComponents]
  lazy val prefix: String = "/"
  lazy val router: Router = wire[Routes]

  lazy val idGenerator = wire[UniqueIdGenerator]
  lazy val timeGenerator = wire[RealTimeGenerator]
  lazy val offerConverter = wire[OfferConverter]

  lazy val searchService = wire[SearchService]

  lazy val repository = wire[CacheBasedRepository]
  lazy val applicationController = wire[Application]

  val onStart = {
    Logger.info("The app is about to start.")
  }

  applicationLifecycle.addStopHook { () =>
    Logger.info("The app is about to stop.")
    Future.successful(Unit)
  }
}