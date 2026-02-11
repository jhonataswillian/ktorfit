package com.example.repositories

import com.example.models.Workout

interface WorkoutRepository {

    suspend fun allWorkouts(): List<Workout>

    suspend fun workoutById(id: Int): Workout?

    suspend fun addWorkout(workout: Workout): Workout

    suspend fun deleteWorkout(id: Int): Boolean

    suspend fun updateWorkout(id: Int, workout: Workout): Boolean
}