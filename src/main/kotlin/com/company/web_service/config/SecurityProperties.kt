package com.company.web_service.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "security")
class SecurityProperties {

    lateinit var  permitAllUris: List<String>

}