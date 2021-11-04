# currency-rest

REST API for looking up conversion rate for currencies.

I chose to use Scala + Play (on Akka).

## Run it

Start server via sbt shell:

```
sbt run
```

Send POST to http://localhost:9000/api/convert
(using parameters: Content-Type -> application/json, Accept -> application/json)
```
{
    "fromCurrency": "GBP",
    "toCurrency" : "EUR",
    "amount" : 102.6
}
```

Should return result:
```
{
    "exchange": 1.164659,
    "amount": 119.4940134,
    "original": 102.6
}
```

## My 50 cents on solving the exercise

Since I didn't write any REST API service before myself, I just picked Play framework, since I found it first and found
a nice tutorial in building the service. I did also read a comparison, where they mentioned that Play is probably the 
slowest of any Scala REST frameworks, so picking another one might also be a choice if bringing it to production.

In general using frameworks like Akka underneath might be a good choice, since they bring already a lot in terms of 
parallelization.

## Next steps

Well, with this it runs on a local machine, but that doesn't solve scaling (adding/removing hosts dynamically), 
monitoring, etc. 

I also already tried to use caching for the client that looks up the rates from another service, but since the response
time was still in the seconds sometimes, I guess it might not work fully. But for the sake of not spending too much 
time on this, I didn't dig into caching.

Play's authors recommend using their framework Lagom https://www.lagomframework.com/ .

## Sources I used to get there

### REST API server implementation

https://www.baeldung.com/scala/play-rest-api

Using SBT template for project setup:
```
sbt new playframework/play-scala-seed.g8
```

### Calling other REST APIs with Play

https://www.playframework.com/documentation/2.8.x/ScalaWS
