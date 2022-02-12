package br.dev.multicode.games.api.resources

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import br.dev.multicode.games.services.GameService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/games"],
produces = [MediaType.APPLICATION_JSON_VALUE],
consumes = [MediaType.APPLICATION_JSON_VALUE])
class GameResource (private val gameService: GameService)
{
    @PostMapping
    fun postGame(@RequestBody @Valid gameRequest: GameRequest): ResponseEntity<GameResponse> =
            ResponseEntity.status(HttpStatus.CREATED)
                    .body(gameService.create(gameRequest))
}