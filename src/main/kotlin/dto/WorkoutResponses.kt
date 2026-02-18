package com.example.dto

import kotlinx.serialization.Serializable

@Serializable
data class WorkoutReport(
    val workoutId: Int,
    val totalVolume: Double,
    val exerciseCount: Int
) {}