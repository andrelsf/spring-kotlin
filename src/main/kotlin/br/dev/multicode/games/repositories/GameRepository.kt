package br.dev.multicode.games.repositories

import br.dev.multicode.games.entities.Game
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
@Transactional(Transactional.TxType.MANDATORY)
internal interface GameRepository : JpaRepository<Game, String>