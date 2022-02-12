package br.dev.multicode.games.services

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import br.dev.multicode.games.entities.Game
import br.dev.multicode.games.repositories.GameRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
@Transactional
class GameService internal constructor(private val gameRepository: GameRepository)
{
    fun create(gameRequest: GameRequest): GameResponse {
        try {
            return gameRepository.save(Game.from(gameRequest))
                    .toGameResponse()
        } catch (e: Exception) {
            throw Exception("Failed to save game. ERROR: ".plus(e.message), e)
        }
    }
}