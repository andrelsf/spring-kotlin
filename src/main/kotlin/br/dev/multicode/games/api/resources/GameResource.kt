package br.dev.multicode.games.api.resources

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.requests.PatchGameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import br.dev.multicode.games.services.GameService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping(
    value = ["/api/games"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE])
class GameResource (private val gameService: GameService)
{
    @PostMapping
    fun postGame(@RequestBody @Valid gameRequest: GameRequest): ResponseEntity<GameResponse> =
        ResponseEntity.status(HttpStatus.CREATED)
                .body(gameService.create(gameRequest))

    @GetMapping
    fun getAllGames(@RequestParam(name = "offset", required = false) offset: Int,
        @RequestParam(name = "limit", required = false) limit: Int): ResponseEntity<List<GameResponse>> =
        ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                    .body(gameService.findAll(offset, limit).content)

    @GetMapping("/{gameId}")
    fun getGame(@PathVariable("gameId") gameId: UUID): ResponseEntity<GameResponse>  =
        ResponseEntity.ok(gameService.findById(gameId))

    @PutMapping("/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun putGame(@PathVariable("gameId") gameId: UUID, @RequestBody @Valid gameRequest: GameRequest) =
        gameService.update(gameId, gameRequest)

    @PatchMapping("/{gameId}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    fun patchGame(@PathVariable("gameId") gameId: UUID, @RequestBody patchGameRequest: PatchGameRequest) =
            gameService.updatePartialContent(gameId, patchGameRequest)

    @DeleteMapping("/{gameId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteGame(@PathVariable("gameId") gameId: UUID) =
            gameService.delete(gameId)
}