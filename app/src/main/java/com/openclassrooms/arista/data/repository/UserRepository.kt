package com.openclassrooms.arista.data.repository

//import com.openclassrooms.arista.data.FakeApiService
import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first

//class UserRepository(private val apiService: FakeApiService = FakeApiService()) {

/**
 * UserRepository gère l'accès aux données de l'utilisateur en utilisant UserDtoDao.
 * Il expose des méthodes pour interagir avec les données utilisateur de manière simple
 * pour les autres couches de l'application (ex. ViewModel).
 */
class UserRepository(private val userDao: UserDtoDao) {
    /**
     * Récupère l'utilisateur stocké dans la base de données.
     * Cette fonction est suspendue, car elle effectue une opération de récupération asynchrone.
     *
     * @return un objet User s'il existe, sinon null.
     */
    suspend fun getUser(): User? {
        return userDao.getUser()
            .first() // Collecte la première émission du Flow, ce qui nous donne l'objet UserDto
            ?.let { User.fromDto(it) } // Convertit le UserDto en User
    }

    /**
     * Insère ou met à jour un utilisateur dans la base de données.
     * Cette fonction est suspendue pour qu'elle soit appelée dans un contexte asynchrone.
     *
     * @param user L'objet User à insérer ou mettre à jour.
     */
    suspend fun insertOrUpdateUser(user: User) {
        userDao.insertUser(user.toDto()) // Convertit le User en UserDto avant de l'insérer
    }

    /**
     * Supprime un utilisateur de la base de données à partir de son identifiant.
     * Cette fonction est suspendue, car elle effectue une opération de suppression asynchrone.
     *
     * @param user L'objet User à supprimer de la base.
     */
    suspend fun deleteUser(user: User) {
        userDao.deleteUserById(user.id) // Supprime l'utilisateur en utilisant son ID
    }

    suspend fun getUserById(userId: Long): User? {
        return userDao.getUserById(userId)
            .first()
            ?.let { User.fromDto(it) }
    }
}