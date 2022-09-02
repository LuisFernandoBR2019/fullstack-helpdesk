package com.luis.helpdesk.resources.exceptions

import com.luis.helpdesk.domain.exceptions.ObjectNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException::class)
    fun objectNotFoundException(
        exception: ObjectNotFoundException, request: HttpServletRequest
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

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrityViolationException(
        exception: DataIntegrityViolationException, request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        val error = StandardError(
            System.currentTimeMillis(),
            HttpStatus.BAD_REQUEST.value(),
            "Violação de dados",
            exception.message,
            request.requestURI
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validationErrors(
        exception: MethodArgumentNotValidException, request: HttpServletRequest
    ): ResponseEntity<StandardError> {
        val errors = ValidationError(
            System.currentTimeMillis(),
            HttpStatus.BAD_REQUEST.value(),
            "Validation error",
            "Erro na validação dos campos",
            request.requestURI
        )

        for (x: FieldError in exception.bindingResult.fieldErrors) {
            x.defaultMessage?.let {
                errors.addError(x.field, it)
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors)
    }
}