package br.dev.multicode.games.services

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import br.dev.multicode.games.entities.Game
import br.dev.multicode.games.repositories.GameRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
@Transactional
class GameService internal constructor(private val gameRepository: GameRepository)
{
    fun create(gameRequest: GameRequest): GameResponse
    {
        try {
            return gameRepository.save(Game.from(gameRequest))
                    .toGameResponse()
        } catch (e: Exception) {
            throw Exception("Failed to save game. ERROR: ".plus(e.message), e)
        }
    }

    fun findById(gameId: UUID): GameResponse
    {
        return gameRepository.findById(gameId.toString())
                .map { gameFound -> gameFound.toGameResponse() }
                .orElseThrow { EntityNotFoundException() }
    }

    fun findAll(offset: Int, limit: Int): Page<GameResponse> {
        return gameRepository.findAll(PageRequest.of(offset, limit))
                .map { game -> game.toGameResponse() }
    }
}