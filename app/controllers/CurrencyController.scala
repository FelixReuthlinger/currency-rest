package controllers

import controllers.CurrencyController.{DEFAULT_FUTURE_TIMEOUT, LOOKUP_URL}
import model.{ConvertRequest, ConvertResponse}
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import java.util.concurrent.TimeUnit
import javax.inject.Inject
import scala.concurrent.duration.{Duration, FiniteDuration}
import scala.concurrent.{Await, ExecutionContext, Future}

class CurrencyController @Inject()
(val controllerComponents: ControllerComponents, val ws: WSClient,
 implicit val ec: ExecutionContext)
  extends BaseController {

  def convert: Action[AnyContent] = Action { implicit request =>
    val content = request.body
    val jsonObject = content.asJson
    val convertRequest: Option[ConvertRequest] =
      jsonObject.flatMap(
        Json.fromJson[ConvertRequest](_).asOpt
      )

    convertRequest match {
      case Some(validRequest) =>
        Ok(Json.toJson(ConvertResponse(fromRequest = validRequest,
          conversionRate = lookupCurrencyRates(validRequest.fromCurrency, validRequest.toCurrency))))
      case None =>
        BadRequest
    }
  }

  def lookupCurrencyRates(currencyFrom: String, currencyTo: String): Double = {
    val result: Future[Double] = ws.url(LOOKUP_URL(currencyFrom)).get().map { response =>
      (response.json \ currencyTo).as[Double]
    }
    Await.result(result, DEFAULT_FUTURE_TIMEOUT)
  }
}

object CurrencyController {
  private final val DEFAULT_FUTURE_TIMEOUT: FiniteDuration = Duration(5, TimeUnit.SECONDS)
  private final val LOOKUP_URL: String => String = currency => s"http://943r6.mocklab.io/exchange-rates/$currency"
}
