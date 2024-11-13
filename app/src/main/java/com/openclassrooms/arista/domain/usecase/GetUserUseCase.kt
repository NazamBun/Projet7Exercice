package com.openclassrooms.arista.domain.usecase

import com.openclassrooms.arista.data.repository.UserRepository
import com.openclassrooms.arista.domain.model.User
import javax.inject.Inject

//class GetUserUsecase @Inject constructor(private val userRepository: UserRepository) {
//    fun execute(): User {
//        return userRepository.user
//    }
//}

/**
 * Use case pour obtenir les données utilisateur.
 */
class GetUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    /**
     * Exécute la récupération des données utilisateur de manière asynchrone.
     *
     * @return L'utilisateur stocké dans la base de données, ou null s'il n'existe pas.
     */
    suspend fun execute(): User? {
        return userRepository.getUser()
    }
}