package com.example.routes

import com.example.dto.CreateExerciseRequest
import com.example.dto.toModel
import com.example.repositories.ExerciseRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.exerciseRoutes(repository: ExerciseRepository) {

    route("/exercises") {
        // Criar Exercício
        post {
            try {
                val exercise = call.receive<CreateExerciseRequest>()
                val newExercise = repository.addExercise(exercise.toModel())
                call.respond(HttpStatusCode.Created, newExercise)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest,
                    "Error creating exercise: ${e.localizedMessage} ")
            }
        }

        // Deletar Exercício
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                return@delete
            }

            val deleted = repository.deleteExercise(id)
            if (deleted) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, "Exercise Not Found")
            }
        }
    }

    route("/workouts/{id}/exercises") {
        get {
            val workoutId = call.parameters["id"]?.toIntOrNull()
            if (workoutId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid Workout ID")
                return@get
            }

            val exercises = repository.exercisesByWorkout(workoutId)
            call.respond(HttpStatusCode.OK, exercises)
        }
    }
}