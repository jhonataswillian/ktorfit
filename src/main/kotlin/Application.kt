package com.example

import com.example.plugins.configureDatabases
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import com.example.repositories.ExposedExerciseRepository
import com.example.repositories.ExposedWorkoutRepository
import com.example.routes.exerciseRoutes
import com.example.routes.workoutRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureMonitoring()
    configureRouting()

    val workoutRepository = ExposedWorkoutRepository()
    val exerciseRepository = ExposedExerciseRepository()

    routing {
        workoutRoutes(workoutRepository)
        exerciseRoutes(exerciseRepository)
    }
}
