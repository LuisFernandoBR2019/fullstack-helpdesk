package com.luis.helpdesk.config

import com.luis.helpdesk.services.DBService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
class DevConfig {

    @Autowired
    private lateinit var dbService: DBService

    @Value("\${spring.jpa.hibernate.ddl-auto}")
    var value: String? = null

    @Bean
    fun instanciaDB() {
        if (value.equals("create")) {
            this.dbService.instanciaDB()
        }
    }

}