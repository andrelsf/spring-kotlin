package br.dev.multicode.games.api.erros

import br.dev.multicode.games.api.erros.Message.GAME_NOT_FOUND
import br.dev.multicode.games.api.erros.Message.OOPS
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {

    var logger: Logger = LoggerFactory.getLogger(ErrorHandler::class.java)

    @ExceptionHandler(EmptyResultDataAccessException::class)
    fun emptyResultDataAccessExceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse>
    {
        logger.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse(GAME_NOT_FOUND.code, GAME_NOT_FOUND.message))
    }

    @ExceptionHandler(Exception::class)
    fun exceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse>
    {
        logger.error(exception.message, exception)
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(ErrorResponse(OOPS.code, OOPS.message));
    }
}