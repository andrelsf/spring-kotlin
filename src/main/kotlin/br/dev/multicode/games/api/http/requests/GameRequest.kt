package br.dev.multicode.games.api.http.requests

import br.dev.multicode.games.entities.Platform
import org.springframework.validation.annotation.Validated
import java.math.BigDecimal
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Validated
data class GameRequest (
    @NotBlank var name: String,
    @NotBlank var description: String,
    @NotNull var platform: Platform,
    @NotNull var price: BigDecimal)