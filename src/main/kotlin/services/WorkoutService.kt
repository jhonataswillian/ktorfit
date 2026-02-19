package com.example.services

import com.example.dto.CreateWorkoutRequest
import com.example.dto.WorkoutReport
import com.example.dto.toModel
import com.example.models.Workout
import com.example.repositories.ExerciseRepository
import com.example.repositories.WorkoutRepository

class WorkoutService(
    private val repository: WorkoutRepository,
    private val exerciseRepository: ExerciseRepository
) {

    suspend fun create(request: CreateWorkoutRequest): Workout {
        validateRequest(request)
        val model = request.toModel()
        return repository.addWorkout(model)
    }

    suspend fun findAll(page: Int = 1, limit: Int = 10): List<Workout> {
        require(page > 0) { "A página deve ser maior que zero" }
        require(limit in 1..100) { "O limite deve estar entre 1 e 100 "}

        val offset = ((page - 1) * limit).toLong()
        return repository.allWorkouts(limit, offset)
    }

    suspend fun findById(id: Int): Workout? {
        return repository.workoutById(id)
    }

    suspend fun delete(id: Int): Boolean {
        return repository.deleteWorkout(id)
    }

    suspend fun update(id: Int, request: CreateWorkoutRequest): Boolean {
        validateRequest(request)
        val model = request.toModel()
        return repository.updateWorkout(id, model)
    }

    // Função para validar o request
    private fun validateRequest(request: CreateWorkoutRequest) {
        if (request.durationMinutes < 5) {
            throw IllegalArgumentException("A duração deve ser de pelo menos 5 minutos")
        }
    }

    suspend fun generateReport(workoutId: Int): WorkoutReport {

        val workout = repository.workoutById(workoutId)
            ?: throw IllegalArgumentException("Treino não encontrado")

        val exercises = exerciseRepository.exercisesByWorkout(workoutId)

        val totalVolume = exercises.sumOf { it.weight * it.sets * it.reps }

        return WorkoutReport(
            workoutId = workout.id!!,
            totalVolume = totalVolume,
            exerciseCount = exercises.size
        )
    }
}