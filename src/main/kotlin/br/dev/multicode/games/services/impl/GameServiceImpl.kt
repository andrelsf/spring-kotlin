package br.dev.multicode.games.services.impl

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.requests.PatchGameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import br.dev.multicode.games.entities.Game
import br.dev.multicode.games.repositories.GameRepository
import br.dev.multicode.games.services.GameService
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@Service
@Transactional
class GameServiceImpl internal constructor(
        private val gameRepository: GameRepository): GameService
{
    override fun create(gameRequest: GameRequest): GameResponse
    {
        try {
            return gameRepository.save(Game.from(gameRequest))
                    .toGameResponse()
        } catch (e: Exception) {
            throw Exception("Failed to save game. ERROR: ".plus(e.message), e)
        }
    }

    override fun findById(gameId: UUID): GameResponse
    {
        return gameRepository.findById(gameId.toString())
                .map { gameFound -> gameFound.toGameResponse() }
                .orElseThrow { EntityNotFoundException() }
    }

    override fun findAll(offset: Int, limit: Int): Page<GameResponse>
    {
        return gameRepository.findAll(PageRequest.of(offset, limit))
                .map { game -> game.toGameResponse() }
    }

    override fun update(gameId: UUID, gameRequest: GameRequest)
    {
        gameRepository.findById(gameId.toString())
                .ifPresentOrElse({gameFound ->
                    gameRepository.save(gameFound.fillWith(gameFound, gameRequest)) },
                { EntityNotFoundException() })
    }

    override fun delete(gameId: UUID)
    {
        try {
            gameRepository.deleteById(gameId.toString())
        } catch (e: EmptyResultDataAccessException) {
            throw EmptyResultDataAccessException("Failed to delete entity by ID: "
                    .plus(gameId.toString().plus(" ERROR: ".plus(e.message))), Int.MAX_VALUE, e)
        }
    }

    override fun updatePartialContent(gameId: UUID, patchGameRequest: PatchGameRequest)
    {
        gameRepository.findById(gameId.toString())
                .ifPresentOrElse({ gameFound ->
                    gameRepository.save(gameFound.fillWith(gameFound, patchGameRequest)) },
                { EntityNotFoundException() })
    }
}