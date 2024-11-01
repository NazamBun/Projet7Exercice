package com.openclassrooms.arista.domain.model

import com.openclassrooms.arista.data.entity.SleepDto
import java.time.LocalDateTime
import java.time.ZoneOffset

data class Sleep(
    @JvmField
    val id: Long? = null,
    var startTime: LocalDateTime,
    var duration: Int,
    var quality: Int
){
    companion object {
        fun fromDto(dto: SleepDto): Sleep {
            return Sleep(
                id = dto.id,
                startTime = LocalDateTime.ofEpochSecond(dto.startTime / 1000, 0, ZoneOffset.UTC),
                duration = dto.duration,
                quality = dto.quality
            )
        }
    }

    fun toDto(): SleepDto {
        return SleepDto(
            id = this.id ?: 0,
            startTime = this.startTime.toEpochSecond(ZoneOffset.UTC) * 1000,
            duration = duration,
            quality = quality
        )
    }
}

