package com.example.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class Workout(
    val id: Int? = null,
    val name: String,
    val description: String,
    val durationMinutes: Int,
    val date: LocalDateTime
) {}