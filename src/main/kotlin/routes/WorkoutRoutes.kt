package com.example.routes

import com.example.models.Workout
import com.example.repositories.WorkoutRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.workoutRoutes(repository: WorkoutRepository) {
    route("/workouts") {

        // === ROTAS GERAIS (/workouts) ===
        get {
            val workouts = repository.allWorkouts()
            call.respond(HttpStatusCode.OK, workouts)
        }


        post {
            try {
                val workout = call.receive<Workout>()

                val newWorkout = repository.addWorkout(workout)

                call.respond(HttpStatusCode.Created, newWorkout)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Invalid JSON format")
            }
        }

        // === ROTAS ESPECÍFICAS (/workouts/{id} ===
        route("/{id}") {

            // === GET por ID ===
            get {
                val id = call.parameters["id"]?.toIntOrNull()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID format. Must be an integer.")
                    return@get
                }

                val  workout = repository.workoutById(id)

                if (workout != null) {
                    call.respond(HttpStatusCode.OK, workout)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Workout with ID $id not found")
                }
            }

            // === DELETE por ID ===
            delete {
                val id = call.parameters["id"]?.toIntOrNull()

                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                    return@delete
                }

                val deleted = repository.deleteWorkout(id)

                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Workout not found")
                }
            }
        }
    }
}