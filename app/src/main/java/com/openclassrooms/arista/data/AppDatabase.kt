package com.openclassrooms.arista.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.openclassrooms.arista.data.dao.ExerciseDtoDao
import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.data.dao.UserDtoDao
import com.openclassrooms.arista.data.entity.ExerciseDto
import com.openclassrooms.arista.data.entity.SleepDto
import com.openclassrooms.arista.data.entity.UserDto

@Database(entities = [UserDto::class, ExerciseDto::class, SleepDto::class], version = 1, exportSchema = false)
        abstract class AppDataBase : RoomDatabase() {
            abstract fun userDtoDao(): UserDtoDao
            abstract fun exerciseDtoDao(): ExerciseDtoDao
            abstract fun sleepDtoDao(): SleepDtoDao

            companion object {
                @Volatile
                private var INSTANCE: AppDataBase? = null

                fun getDatabase(context: Context): AppDataBase {
                    return INSTANCE ?: synchronized(this) {
                        val instance = Room.databaseBuilder(
                            context.applicationContext,
                            AppDataBase::class.java,
                            "app_database"
                        ).build()
                        INSTANCE = instance
                        instance
                    }
                }
            }
        }