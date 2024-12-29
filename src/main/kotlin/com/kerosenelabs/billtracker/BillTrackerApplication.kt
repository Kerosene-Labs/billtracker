package com.kerosenelabs.billtracker

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.CrossOrigin

@OpenAPIDefinition(
    servers = [
        Server(url = "https://billtracker.kerosenelabs.com/api/v1", description = "Production"),
        Server(url = "https://dev.billtracker.kerosenelabs.com/api/v1", description = "Development"),
        Server(url = "http://localhost:8080", description = "Local")
    ]
)
@CrossOrigin
@SpringBootApplication
open class BillTrackerApplication

fun main(args: Array<String>) {
    runApplication<BillTrackerApplication>(*args)
}
