package com.openclassrooms.arista.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openclassrooms.arista.data.entity.UserDto
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDtoDao {

    /**
     * Insère un utilisateur dans la base de données. Si un utilisateur avec le même identifiant
     * existe déjà, il sera remplacé.
     *
     * @param user L'objet UserDto à insérer ou mettre à jour.
     * @return L'identifiant de l'utilisateur inséré.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDto): Long

    /**
     * Récupère l'utilisateur stocké dans la base de données.
     *
     * @return Un Flow contenant l'objet UserDto ou null s'il n'existe pas.
     */
    @Query("SELECT * FROM user LIMIT 1")
    fun getUser(): Flow<UserDto>

    /**
     * Supprime un utilisateur de la base de données par son identifiant.
     *
     * @param id L'identifiant de l'utilisateur à supprimer.
     */
    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteUserById(id: Long)

}