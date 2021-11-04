# Exercise
Create a single-endpoint REST API that consumes data from an external service. The endpoint
should expose the following interface:
```
POST /api/convert
Body:
{
"fromCurrency": "GBP",
"toCurrency" : "EUR",
"amount" : 102.6
}
```
The API response payload should be an object detailing the exchange rate between
"fromCurrency" and "toCurrency" and the amount converted into the target currency.
```
{
"exchange" : 1.11,
"amount" : 113.886,
"original" : 102.6
}
```
Exchange rates should be loaded from an external service through the following endpoint:
http://943r6.mocklab.io/exchange-rates/:currency, where valid currencies are: `GBP`, `USD`,
`EUR`, `CHF`, and `CNY`. The exchange rate values returned by this mock service are actually
static, however your implementation should assume that this data will change over time.

## Submission notes:
● Feel free to pick your libraries of choice to get the job done, the only constraint is to
implement the exercise in Scala.
● Explain your assumptions in a README file. Consider mentioning what you would do
next if you were to productionise this service, consuming real data and exposing it to a
real audience.
● Try to time-box this assignment to 3-5 hours max. You don’t need to polish your
submission excessively. The main purpose of this exercise is to give us a sense of your
approach to designing and building software.
● There’s no need to deploy your service online as long as we can build it and run locally.