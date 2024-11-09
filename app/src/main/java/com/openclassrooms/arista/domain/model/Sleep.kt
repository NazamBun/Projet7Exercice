package com.openclassrooms.arista.domain.model


import java.util.TimeZone
import com.openclassrooms.arista.data.entity.SleepDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Sleep(
    val id: Long? = null, // Identifiant unique du sommeil
    var startTime: LocalDateTime,
    val duration: Int,
    val quality: Int
) {

    companion object {
        /**
         * Convertit un SleepDto (de la base de données) en un objet Sleep.
         */
        fun fromDto(dto: SleepDto): Sleep {
            return Sleep(
                id = dto.id, // Récupère l'ID depuis le DTO
                startTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dto.startTime),
                    TimeZone.getDefault().toZoneId()
                ),
                duration = dto.duration, // Récupère la durée depuis le DTO
                quality = dto.quality // Récupère la qualité depuis le DTO
            )
        }
    }

    /**
     * Convertit un objet Sleep en SleepDto pour la base de données.
     */
    fun toDto(userId: Long): SleepDto {
        return SleepDto(
            id = id ?: throw IllegalArgumentException("Sleep Id should not be null"),
            startTime = startTime.toInstant(ZoneOffset.UTC).toEpochMilli(),
            duration = duration,
            quality = quality,
            userId = userId
        )
    }

}
