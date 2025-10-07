package com.company.web_service.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(private val securityProperties: SecurityProperties) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.authorizeHttpRequests { authorize ->
            securityProperties.permitAllUris.forEach { uri ->
                authorize.requestMatchers(uri).permitAll()
            }
            authorize.anyRequest().authenticated()
        }

        http.csrf { it.disable() }
        http.httpBasic { }

        return http.build()
    }

}


