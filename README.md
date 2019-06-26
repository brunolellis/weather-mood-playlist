# weather mood playlist

## Technology stack

- Java 11
- Spring Boot 2.1.6
- WebFlux (Reactor)
- Swagger
- Docker
- docker-compose


## API documentation

The API documentation is embedded and available on http://localhost:8080/swagger.yaml.




$ `curl localhost:8080/api/v1/playlist/city/Campinas`

$ `curl localhost:8080/api/v1/playlist/coordinates?lat=-22,9099&lon=-47,0626`



## Improvements

- Improve local cache;
- `@Cacheable` is not compatible with reactor (Mono and Flux) yet. So it's been temporarily implemented _by hand_;
- Implement more tests and with better scenarios (forbidden access, internal server error ...);
