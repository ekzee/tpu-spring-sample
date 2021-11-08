package ru.tpu.todo

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Дополнение ко всем контроллерам приложения
 */
@ControllerAdvice
class GlobalExceptionControllerAdvice {

    /**
     * При выбрасывании ошибки в методе контроллера она не пробросится до верхнего уровня, а уйдет в этот метод. Здесь
     * мы добавляем свою обработку этой ошибки и возвращаем JSONку в нужном нам формате.
     */
    @ExceptionHandler(LogicException::class)
    fun handleException(exception: LogicException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse(
                exception.httpCode,
                exception.message!!
            ),
            HttpStatus.resolve(exception.httpCode)!!
        )
    }
}

data class ErrorResponse(
    val code: Int,
    val message: String
)