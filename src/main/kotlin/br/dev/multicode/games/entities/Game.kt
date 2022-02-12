package br.dev.multicode.games.entities

import br.dev.multicode.games.api.http.requests.GameRequest
import br.dev.multicode.games.api.http.responses.GameResponse
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.UpdateTimestamp
import java.math.BigDecimal
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "tb_games")
data class Game (
    @Id
    @Column(name = "game_id", length = 37)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private val gameId: String? = null,

    @Column(nullable = false)
    val name: String? = null,

    @Column(nullable = false)
    val description: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    val platform: Platform? = null,

    @Column(nullable = false)
    val price: BigDecimal? = null,

    private var createdAt: ZonedDateTime? = ZonedDateTime.now(),
    private var updatedAt: ZonedDateTime? = ZonedDateTime.now())
{
    fun toGameResponse(): GameResponse = GameResponse(
            gameId = this.gameId.toString(),
            name = this.name.toString(),
            description = this.description.toString(),
            platform = this.platform.toString(),
            price = this.price.toString())

    companion object {
        fun from(gameRequest: GameRequest) = Game(
                name = gameRequest.name,
                description = gameRequest.description,
                platform = gameRequest.platform,
                price = gameRequest.price)
    }
}