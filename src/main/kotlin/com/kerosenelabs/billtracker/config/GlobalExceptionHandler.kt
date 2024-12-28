package com.kerosenelabs.billtracker.config

import com.kerosenelabs.billtracker.exception.AuthException
import com.kerosenelabs.billtracker.exception.UnconfirmedUserException
import com.kerosenelabs.billtracker.model.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(AuthException::class)
    fun handleAuthException(e: AuthException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorResponse(e.message.toString()))
    }

    @ExceptionHandler(UnconfirmedUserException::class)
    fun handleUnconfirmedUserException(e: UnconfirmedUserException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErrorResponse(e.message.toString()))
    }
}
