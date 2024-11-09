package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import javax.inject.Inject

//class AddNewExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
//    fun execute(exercise: Exercise) {
//        exerciseRepository.addExercise(exercise)
//    }
//}

class AddNewExerciseUseCase @Inject constructor(private val exerciseRepository: ExerciseRepository) {
    /**
     * Exécute l'ajout de l'exercice de manière asynchrone.
     *
     * @param exercise L'exercice à ajouter.
     */
    suspend fun execute(exercise: Exercise) {
        exerciseRepository.addExercice(exercise)
    }
}