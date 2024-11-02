package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

class GetAllExercisesUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    suspend fun execute(): List<Exercise> {
        return try {
            exerciseRepository.getAllExercices()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Retourne une liste vide ou une autre action en cas d'erreur
        }
    }
}