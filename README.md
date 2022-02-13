# Kotlin, Spring, PostgreSQL and Liquibase Database Migrations

Learning Kotlin for backend with Spring, Database Migrations Liquibase and PostgreSQL

## Usage

Tools:
- [HTTPie](https://httpie.io/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker-compose](https://docs.docker.com/compose/install/)

### Run:
```shell
docker-compose up -d
mvn spring-boot:run
```

### Usage:

Examples in directory `requests`

`POST` a new Game
```shell
http --json POST :8090/api/games < requests/postANewGame.json
```

`GET` All games
```shell
http --json GET :8090/api/games offset==0 limit==50
```


`GET` a single game by ID
```shell
http --json GET :8090/api/games/89d36599-3af7-459b-889d-ec655d38769f
```
Result:
```shell
HTTP/1.1 200 
Connection: keep-alive
Content-Type: application/json
Date: Sun, 13 Feb 2022 13:08:54 GMT
Keep-Alive: timeout=60
Transfer-Encoding: chunked

{
    "description": "Claiton bom de guerra",
    "gameId": "89d36599-3af7-459b-889d-ec655d38769f",
    "name": "Bom de Guerra",
    "platform": "PS4",
    "price": "79.90"
}
```

`PUT` a single game by ID
```shell
http --json PUT :8090/api/games/89d36599-3af7-459b-889d-ec655d38769f \ 
 < requests/putAGameRequest.json
```

`PATCH` a single game by ID
```shell
http --json PATCH :8090/api/games/89d36599-3af7-459b-889d-ec655d38769f \
 < requests/patchAGameRequest_price.json
```

`DELETE` a single game by ID
```shell
http --json DELETE :8090/api/games/89d36599-3af7-459b-889d-ec655d38769f
```


