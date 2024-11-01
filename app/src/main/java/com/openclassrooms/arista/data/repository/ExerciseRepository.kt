package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

class ExerciseRepository(private val exerciseDao: ExerciseDtoDao) {

    // Get all exercises
    suspend fun getAllExercices(): List<Exercise> {
        return exerciseDao.getAllExercises()
            .first()
            .map { Exercise.fromDto(it) }
    }

    // Add an exercise
    suspend fun addExercise(exercise: Exercise) {
        exerciseDao.insertExercise(exercise.toDto())
    }

    // Delete an exercise
    suspend fun deleteExercice(exercice: Exercise) {
        exercice.id?.let {
            exerciseDao.deleteExerciseById(
                id = exercice.id
            )
        }
    }
}