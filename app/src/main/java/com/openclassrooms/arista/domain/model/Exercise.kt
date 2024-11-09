package com.openclassrooms.arista.domain.model

import java.util.TimeZone
import com.openclassrooms.arista.data.entity.ExerciceDto
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Exercise(
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var category: ExerciseCategory,
    var intensity: Int
) {
    companion object {
        fun fromDto(dto: ExerciceDto): Exercise {
            return Exercise(
                id = dto.id,
                startTime = LocalDateTime.ofInstant(
                    Instant.ofEpochMilli(dto.startTime),
                    TimeZone.getDefault().toZoneId()
                ),
                duration = dto.duration,
                category = ExerciseCategory.valueOf(dto.category),
                intensity = dto.intensity
            )
        }
    }

    fun toDto(userId: Long)= ExerciceDto (
        id = id ?: throw IllegalArgumentException("Exercise Id should not be null"),
        startTime = startTime.toInstant(ZoneOffset.UTC).toEpochMilli(),
        duration = duration,
        category = category.name,
        intensity = intensity,
        userId = userId
    )
}