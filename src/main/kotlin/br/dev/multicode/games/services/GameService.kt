package br.dev.multicode.games.services

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.requests.PatchGameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import org.springframework.data.domain.Page
import java.util.*

interface GameService {
    fun delete(gameId: UUID)
    fun findById(gameId: UUID): GameResponse
    fun update(gameId: UUID, gameRequest: GameRequest)
    fun create(gameRequest: GameRequest): GameResponse
    fun findAll(offset: Int, limit: Int): Page<GameResponse>
    fun updatePartialContent(gameId: UUID, patchGameRequest: PatchGameRequest)
}