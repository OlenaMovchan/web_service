package com.example.kotlin.exceptionhandler.dto

data class ResponseExceptionDto(
    var timeStamp: String = "",
    var status: Int = 0,
    var message: String = "",
    var code: Int = 0,
)

