package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.openclassrooms.arista.data.entity.ExerciceDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciceDtoDao {

    // Insère un nouvel exercice dans la table "exercice"
    @Insert
    suspend fun insertExercice(exercice: ExerciceDto): Long

    // Récupère tous les exercices de la table "exercice" sous forme de Flow
    @Query("SELECT * FROM exercice")
    fun getAllExercices(): Flow<List<ExerciceDto>>

    // Supprime un exercice de la table "exercice" en fonction de son id
    @Query("DELETE FROM exercice WHERE id = :id")
    suspend fun deleteExerciceById(id: Long)
}