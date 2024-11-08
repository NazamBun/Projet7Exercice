package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.UserDto

data class User(
    var id: Long, //Identifiant unique de l'uutilisateur
    var name: String,
    var email: String,
    var password: String
) {
    /**
     * Méthode statique qui convertit un objet UserDto (provenant de la base de données)
     * en un objet User (modèle de domaine).
     */
    companion object {
        fun fromDto(dto: UserDto): User {
            return User(
                id = dto.id, // Récupère l'ID depuis le DTO
                name = dto.name, // Récupère le nom depuis le DTO
                email = dto.email, // Récupère l'email depuis le DTO
                password = dto.password // Récupère le mot de passe depuis le DTO
            )
        }
    }

    /**
     * Méthode d'instance qui convertit un objet User en un UserDto.
     * Cela est nécessaire pour sauvegarder les données dans la base de données.
     */
    fun toDto(): UserDto {
        return UserDto(
            id = id, // Passe l'ID à l'objet UserDto
            name = name, // Passe le nom à l'objet UserDto
            email = email, // Passe l'email à l'objet UserDto
            password = password // Passe le mot de passe à l'objet UserDto
        )
    }
}