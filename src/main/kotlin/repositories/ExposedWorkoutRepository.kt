package com.example.repositories

import com.example.database.Workouts
import com.example.models.Workout
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

class ExposedWorkoutRepository : WorkoutRepository {

    private suspend fun <T> dbQuery(block: suspend () -> T) : T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    private fun resultRowToWorkout(row: ResultRow) = Workout(
        id = row[Workouts.id],
        name = row[Workouts.name],
        description = row[Workouts.description],
        durationMinutes = row[Workouts.durationMinutes]
    )

    override suspend fun allWorkouts(): List<Workout> = dbQuery {
        Workouts.selectAll().map { resultRowToWorkout(it) }
    }

    override suspend fun addWorkout(workout: Workout): Workout = dbQuery {
        val insertStatement = Workouts.insert {
            it[name] = workout.name
            it[description] = workout.description
            it[durationMinutes] = workout.durationMinutes
        }

        val id = insertStatement[Workouts.id]

        workout.copy(id = id)
    }

    override suspend fun workoutById(id: Int): Workout? = null
    override suspend fun deleteWorkout(id: Int): Boolean = false
    override suspend fun updateWorkout(id: Int, workout: Workout): Boolean = false
}