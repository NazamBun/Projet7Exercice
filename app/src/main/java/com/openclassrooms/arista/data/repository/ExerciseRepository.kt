package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.FakeApiService
import com.openclassrooms.arista.data.dao.ExerciceDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

//class ExerciseRepository(private val apiService: FakeApiService = FakeApiService()) {

class ExerciseRepository(private val exerciceDao: ExerciceDtoDao) {


    suspend fun getAllExercises(): List<Exercise> {
        return exerciceDao.getAllExercices()
            .first()
            .map { Exercise.fromDto(it) }
    }

    suspend fun addExercice(exercice: Exercise) {
        exerciceDao.insertExercice(exercice.toDto())
    }

    suspend fun deleteExercice(exercice: Exercise) {
        exercice.id?.let {
            exerciceDao.deleteExerciceById(
                id = exercice.id
            )
        }
    }
}