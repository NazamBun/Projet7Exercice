package com.openclassrooms.arista.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.openclassrooms.arista.domain.model.User

@Entity(tableName = "user")
data class UserDto (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "password")
    var password: String
) {
    fun toModel(): User {
        return User(
            name = this.name,
            email = this.email,
            id = this.id,
            password = this.password
        )
    }
}