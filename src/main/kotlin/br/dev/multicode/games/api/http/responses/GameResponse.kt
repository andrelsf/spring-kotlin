package br.dev.multicode.games.api.http.responses

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(value= JsonInclude.Include.NON_NULL)
data class GameResponse (
        var gameId: String,
        var name: String,
        var description: String,
        var platform: String,
        var price: String)