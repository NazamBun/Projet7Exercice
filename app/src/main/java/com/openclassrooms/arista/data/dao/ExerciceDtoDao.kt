package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openclassrooms.arista.data.entity.ExerciceDto
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciceDtoDao {

    // Insère un nouvel exercice dans la table "exercice"
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercice(exercice: ExerciceDto): Long

    // Récupère tous les exercices de la table "exercice" sous forme de Flow
    @Query("SELECT * FROM exercise")
    fun getAllExercises(): Flow<List<ExerciceDto>>

    // Supprime un exercice de la table "exercice" en fonction de son id
    @Query("DELETE FROM exercise WHERE id = :id")
    suspend fun deleteExerciceById(id: Long)

    @Query("SELECT * FROM exercise WHERE userId = :id")
    fun getExercisesByUserId(id: Long): Flow<List<ExerciceDto>>
}