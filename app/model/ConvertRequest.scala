package model

import model.ConvertRequest.CURRENCIES_VALID
import play.api.libs.json.{Json, OFormat}


case class ConvertRequest(fromCurrency: String, toCurrency: String, amount: Double) {
  require(CURRENCIES_VALID.contains(fromCurrency), s"currency (from) '$fromCurrency' not in supported types: $CURRENCIES_VALID")
  require(CURRENCIES_VALID.contains(toCurrency), s"currency (to) '$toCurrency' not in supported types: $CURRENCIES_VALID")
}

object ConvertRequest {

  implicit val newTodoListJson: OFormat[ConvertRequest] = Json.format[ConvertRequest]

  private final val CURRENCY_GBP = "GBP"
  private final val CURRENCY_USD = "USD"
  private final val CURRENCY_EUR = "EUR"
  private final val CURRENCY_CHF = "CHF"
  private final val CURRENCY_CNY = "CNY"

  private final val CURRENCIES_VALID = Seq(CURRENCY_CHF, CURRENCY_CNY, CURRENCY_EUR, CURRENCY_GBP, CURRENCY_USD)
}