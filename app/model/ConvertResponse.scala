package model

import play.api.libs.json.{Json, OFormat}

case class ConvertResponse(exchange: Double, amount: Double, original: Double)

object ConvertResponse {
  implicit val newTodoListJson: OFormat[ConvertResponse] = Json.format[ConvertResponse]

  def apply(fromRequest: ConvertRequest, conversionRate: Double): ConvertResponse = ConvertResponse(
    exchange = conversionRate,
    amount = fromRequest.amount * conversionRate,
    original = fromRequest.amount)
}