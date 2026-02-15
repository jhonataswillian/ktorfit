package com.example.dtos

import com.example.models.Exercise
import kotlinx.serialization.Serializable

@Serializable
data class CreateExerciseRequest(
    val name: String,
    val weight: Double,
    val sets: Int,
    val reps: Int,
    val workoutId: Int
)

fun CreateExerciseRequest.toModel(): Exercise {
    return Exercise(
        id = null,
        name = this.name,
        weight = this.weight,
        sets = this.sets,
        reps = this.reps,
        workoutId = this.workoutId
    )
}