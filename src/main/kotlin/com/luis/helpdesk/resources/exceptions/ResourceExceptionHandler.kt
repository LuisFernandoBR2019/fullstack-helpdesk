package com.luis.helpdesk.resources.exceptions

import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler
    fun objectNotFoundException(
        exception: ObjectNotFoundException,
        request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        val error = StandardError(
            System.currentTimeMillis(),
            HttpStatus.NOT_FOUND.value(),
            "Object Not Found",
            exception.message,
            request.requestURI
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }
}