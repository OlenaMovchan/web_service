package com.company.web_service

import com.company.web_service.config.SecurityProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(SecurityProperties::class)
@SpringBootApplication
class WebServiceApplication

fun main(args: Array<String>) {
	runApplication<WebServiceApplication>(*args)
}
