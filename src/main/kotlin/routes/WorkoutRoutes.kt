package com.example.routes

import com.example.dto.CreateWorkoutRequest
import com.example.services.WorkoutService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.workoutRoutes(service: WorkoutService) {
    route("/workouts") {

        // === ROTAS GERAIS (/workouts) ===
        get {
            val workouts = service.findAll()
            call.respond(HttpStatusCode.OK, workouts)
        }


        post {
            val workout = call.receive<CreateWorkoutRequest>()
            val newWorkout = service.create(workout)
            call.respond(HttpStatusCode.Created, newWorkout)

        }

        // === ROTAS ESPECÍFICAS (/workouts/{id}) ===
        route("/{id}") {

            // === GET por ID ===
            get {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format. Must be an integer.")
                    return@get
                }

                val  workout = service.findById(id)
                if (workout != null) {
                    call.respond(HttpStatusCode.OK, workout)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Workout with ID $id not found")
                }
            }

            get("/report") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "ID Inválido")
                    return@get
                }

                val report = service.generateReport(id)
                call.respond(HttpStatusCode.OK, report)
            }

            // === DELETE por ID ===
            delete {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                    return@delete
                }

                val deleted = service.delete(id)
                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Workout not found")
                }
            }
        }
    }
}