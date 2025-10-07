package com.company.web_service.exceptionhandler

import com.example.kotlin.exceptionhandler.dto.InvalidFieldDto
import com.example.kotlin.exceptionhandler.dto.ResponseExceptionDto
import com.example.kotlin.exceptionhandler.dto.ResponseValidationExceptionDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler() {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        val httpStatus = HttpStatus.BAD_REQUEST

        val errors = exception.bindingResult.allErrors.map { error ->
            val fieldError = error as FieldError
            InvalidFieldDto(
                field = fieldError.field,
                message = fieldError.defaultMessage ?: "Validation error"
            )
        }

        val responseBody = createResponseValidationExceptionDto(
            httpStatus.value(), "Validation failed!", 1, errors
        )

        logger.error("Validation failed! Errors: $errors", exception)
        return handleExceptionInternal(exception, responseBody, headers, httpStatus, request)
    }

    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(
        exception: ResponseStatusException,
        webRequest: WebRequest
    ): ResponseEntity<Any> {
        val responseBody = createResponseExceptionDto(
            exception.statusCode.value(),
            exception.reason ?: "Error",
            1
        )
        logger.error("Exception occurred: ${exception.message} on request: $webRequest", exception)
        return handleExceptionInternal(exception, responseBody, HttpHeaders(), exception.statusCode, webRequest)!!
    }

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(exception: Exception, webRequest: WebRequest): ResponseEntity<Any> {
        val responseBody = createResponseExceptionDto(
            HttpStatus.BAD_REQUEST.value(),
            exception.message!!,
            10
        )
        logger.error("Exception occurred: ${exception.message} on request: $webRequest", exception)
        return handleExceptionInternal(exception, responseBody, HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest)!!
    }

    private fun createResponseExceptionDto(
        status: Int,
        message: String,
        code: Int,
    ): ResponseExceptionDto {
        return ResponseExceptionDto(
            code = code,
            status = status,
            message = message,
            timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )
    }

    private fun createResponseValidationExceptionDto(
        status: Int,
        message: String,
        code: Int,
        errors: List<InvalidFieldDto>
    ): ResponseValidationExceptionDto {
        return ResponseValidationExceptionDto(
            code = code,
            status = status,
            message = message,
            errors = errors,
            timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )
    }
}