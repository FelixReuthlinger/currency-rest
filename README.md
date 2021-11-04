# currency-rest

REST API for looking up conversion rate for currencies.

I chose to use Scala + Play (on Akka).

## Sources I used to get there

### REST API server implementation

https://www.baeldung.com/scala/play-rest-api

Using SBT template for project setup:
```
sbt new playframework/play-scala-seed.g8
```

### Calling other REST APIs with Play

https://www.playframework.com/documentation/2.8.x/ScalaWS
