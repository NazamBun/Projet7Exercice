package com.openclassrooms.arista.data.repository

import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.domain.model.User
import kotlinx.coroutines.flow.first

class UserRepository(private val userDao: UserDtoDao) {

    suspend fun getUser(): User? {
        return userDao.getUser()
            .first()
            ?.let { User.fromDto(it) }
    }

    suspend fun insertOrUpdateUser(user: User) {
        userDao.insertOrUpdateUser(user.toDto())
    }

    suspend fun deleteUser(user: User) {
        user.id?.let { userDao.deleteUserById(it) }
    }

}