package com.example.kotlin.exceptionhandler.dto

data class ResponseValidationExceptionDto(
    var timeStamp: String = "",
    var status: Int = 0,
    var message: String = "",
    var code: Int = 0,
    var errors: List<InvalidFieldDto> = emptyList(),
)