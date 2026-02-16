package com.example.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.* // Importante para BadRequestException
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.serialization.SerializationException
import org.slf4j.event.Level

fun Application.configureMonitoring() {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
}

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<BadRequestException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "Requisição inválida: ${cause.message}")
        }

        exception<SerializationException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "Formato JSON incorreto: ${cause.message}")
        }

        exception<IllegalArgumentException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "Dados inválidos: ${cause.message}")
        }

        exception<Throwable> { call, cause ->
            cause.printStackTrace() // Log para nós
            call.respond(HttpStatusCode.InternalServerError, "Erro inesperado no servidor. Consulte os logs.")
        }
    }
}