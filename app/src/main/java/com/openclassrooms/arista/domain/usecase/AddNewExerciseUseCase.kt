package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

class AddNewExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    suspend fun execute(exercice: Exercise) {
        exerciseRepository.addExercise(exercice)
    }
}