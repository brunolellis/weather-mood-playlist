# weather mood playlist

## External APIs dependency

- [OpenWeatherMap](https://openweathermap.org/current)
- [Spotify](https://developer.spotify.com/documentation/web-api/)


## Architecture

Following the principles of hexagonal architecture, it clearly separates what is domain logic from its boundaries by using adapters and ports.

![hexagonal architecture](resources/hexagonal.png?raw=true "hexagonal architecture")


Translating it to the Java and Maven world, there are 4 modules:
- playlist: domain
- playlist-api: web adapter or REST API
- openweathermap-api-client: output port to access data
- spotify-api-client: another output port to access data

Using inversion of control, we define interfaces inside our domain and those interfaces are implemented on another module, so we have dependency inversion.

## Reactive API

As we are mainly calling external systems through HTTP calls, ie blocking and synchronous calls, using reactive streams is a good fit.

Note: using reactive programming does not mean that your system will be faster. It means that it can handle more load using even fewer resources.

## Technology Stack

- Java 11
- Spring Boot 2.1.6
- WebFlux (reactor)
- Caffeine
- Lombok
- JUnit
- Mockito
- Swagger
- Docker
- docker-compose
- JMeter

## How to run

- compile, test and package: `./mvnw package`
- start docker compose: `docker-compose up` 
- or stat using: `java -jar playlist-api/target/playlist-api.jar`

## API documentation

The API documentation is embedded and available here: http://localhost:8080/swagger.yaml.

## curl

To receive a playlist based on a city, try the following: `curl localhost:8080/api/v1/playlist/city/Campinas`

Or using geographic coordinates: `curl localhost:8080/api/v1/playlist/coordinates?lat=-22,9099&lon=-47,0626`

## Load test

### JMeter
There is a really simple performance test called `city-performance.jmx` to simulate some load.

### wrk
wrk is another tool to load test an HTTP system. There is a `wrk.log` with some data.


## Improvements
- Spotify's API has rate limiting;
- Improve local cache;
- `@Cacheable` is not compatible with reactor (`Mono` and `Flux`) yet. So it's been temporarily implemented _by hand_;
- Implement more tests and with better scenarios (forbidden access, internal server error ...);
- Improve exception handler (`GlobalExceptionHandler`);
- Monitoring

Thanks very much!