package com.openclassrooms.arista.data.repository

//import com.openclassrooms.arista.data.FakeApiService
import com.openclassrooms.arista.data.dao.SleepDtoDao
import com.openclassrooms.arista.domain.model.Sleep
import kotlinx.coroutines.flow.first

//class SleepRepository(private val apiService: FakeApiService = FakeApiService()) {

/**
 * SleepRepository gère l'accès aux données de sommeil via SleepDtoDao.
 * Il expose des méthodes pour interagir avec les données de sommeil de manière simple
 * pour les autres couches de l'application.
 */
class SleepRepository(private val sleepDao: SleepDtoDao) {

    /**
     * Récupère tous les enregistrements de sommeil depuis la base de données.
     * Cette fonction est suspendue, car elle effectue une opération de récupération asynchrone.
     *
     * @return Une liste d'objets Sleep.
     */
    suspend fun getAllSleep(): List<Sleep> { // C de CRUD
        return sleepDao.getAllSleeps()
            .first() // Collecte la première émission du Flow, ce qui nous donne la liste de SleepDto
            .map { Sleep.fromDto(it) } // Convertit chaque SleepDto en Sleep
    }

    /**
     * Insère un nouvel enregistrement de sommeil dans la base de données.
     * Cette fonction est suspendue, car elle effectue une opération d'insertion asynchrone.
     *
     * @param sleep L'objet Sleep à insérer.
     */
    suspend fun addSleep(sleep: Sleep, userId:Long) { // R de CRUD
        sleepDao.insertSleep(sleep.toDto(userId))// Convertit Sleep en SleepDto avant de l'insérer
    }

    suspend fun deleteSleep(sleep: Sleep) {
        sleep.id?.let {
            sleepDao.deleteSleepById(it) // Supprime le sommeil en utilisant son ID
        }
    }

    suspend fun getSleepsByUserId(userId: Long): List<Sleep> {
        return sleepDao.getSleepsByUserId(userId)
            .first()
            .map { Sleep.fromDto(it) }
    }
}