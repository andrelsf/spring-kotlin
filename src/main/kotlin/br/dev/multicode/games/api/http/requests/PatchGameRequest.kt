package br.dev.multicode.games.api.http.requests

import br.dev.multicode.games.entities.Platform
import java.math.BigDecimal

class PatchGameRequest (
        var name: String? = null,
        var description: String? = null,
        var platform: Platform? = null,
        var price: BigDecimal? = null)