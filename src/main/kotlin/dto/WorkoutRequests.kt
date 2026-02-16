package com.example.dto

import com.example.models.Workout
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateWorkoutRequest(
    val name: String,
    val description: String,
    val durationMinutes: Int,
    val date: LocalDateTime
) {}

fun CreateWorkoutRequest.toModel(): Workout {
    return Workout(
        id = null,
        name = this.name,
        description = this.description,
        durationMinutes = this.durationMinutes,
        date = this.date
    )
}