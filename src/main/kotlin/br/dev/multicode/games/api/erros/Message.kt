package br.dev.multicode.games.api.erros

enum class Message (val code: Int, val message: String)
{
    // 404
    GAME_NOT_FOUND(404, "Game not found"),

    // 422
    OOPS(422, "Oops failed to process request");
}