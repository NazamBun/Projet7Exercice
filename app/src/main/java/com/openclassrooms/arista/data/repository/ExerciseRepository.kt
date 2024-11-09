package com.openclassrooms.arista.data.repository


import com.openclassrooms.arista.data.dao.ExerciceDtoDao
import com.openclassrooms.arista.domain.model.Exercise
import kotlinx.coroutines.flow.first

//class ExerciseRepository(private val apiService: FakeApiService = FakeApiService()) {

class ExerciseRepository(private val exerciceDao: ExerciceDtoDao) {


    suspend fun getAllExercises(): List<Exercise> {
        return exerciceDao.getAllExercises()
            .first()
            .map { Exercise.fromDto(it) }
    }

    suspend fun getExercisesByUserId(userId: Long): List<Exercise> {
        return exerciceDao.getExercisesByUserId(userId)
            .first()
            .map {
                // ou it.toModel()
                Exercise.fromDto(it) }
    }

    suspend fun addExercice(exercice: Exercise, userId: Long) {
        exerciceDao.insertExercice(exercice.toDto(userId))
    }

    suspend fun deleteExercice(exercice: Exercise) {
        exercice.id?.let {
            exerciceDao.deleteExerciceById(
                id = exercice.id
            )
        }
    }
}