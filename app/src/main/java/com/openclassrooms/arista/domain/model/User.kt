package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto


data class User(
    val id: Long? = null,
    var name: String,
    var email: String,
    var password: String
) {
    companion object {
        fun fromDto(dto: UserDto ): User {
            return User(
                id = dto.id,
                name = dto.name,
                email = dto.email,
                password = dto.password
            )
        }
    }

    fun toDto(): UserDto {
        return UserDto(
            id = id ?:0,
            name = name,
            email = email,
            password = password
        )
    }
}