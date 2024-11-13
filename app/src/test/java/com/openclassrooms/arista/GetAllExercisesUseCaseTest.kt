package com.openclassrooms.arista

import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime


// Utilisation de JUnit4 pour exécuter les tests
@RunWith(JUnit4::class)
class GetAllExercisesUseCaseTest {

    // Création d'un mock de ExerciseRepository
    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    // Création d'une instance de GetAllExercisesUseCase
    private lateinit var getAllExercisesUseCase: GetAllExercisesUseCase

    // Méthode exécutée avant chaque test pour initialiser les objets mock
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllExercisesUseCase = GetAllExercisesUseCase(exerciseRepository)
    }



    // Méthode exécutée après chaque test pour nettoyer les mocks
    @After
    fun tearDown() {
        // Efface les mocks pour éviter des interférences entre les tests
        Mockito.framework().clearInlineMocks()
    }



    // Test vérifiant que le use case retourne bien une liste d'exercices lorsque le repository en fournit une
    @Test
    fun `when repository returns exercises, use case should return them`() = runBlocking {
        // Arrange - Préparation des données pour le test
        val fakeExercises = listOf(
            Exercise(
                startTime = LocalDateTime.now(),
                duration = 30,
                category = ExerciseCategory.Running,
                intensity = 5
            ),
            Exercise(
                startTime = LocalDateTime.now().plusHours(1),
                duration = 45,
                category = ExerciseCategory.Riding,
                intensity = 7
            )
        )
        // Configure le mock pour retourner la liste fakeExercises quand getAllExercises est appelé
        Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(fakeExercises)


        // Act - Exécute l'action de test : appelle la méthode execute() du use case
        val result = getAllExercisesUseCase.execute()


        // Assert - Vérifie que le résultat est égal à la liste d'exercices factices
        assertEquals(fakeExercises, result)
    }

    // Test vérifiant que le use case retourne bien une liste vide lorsque le repository en fournit une
    @Test
    fun `when repository returns empty list, use case should return empty list`() = runBlocking {
        // Arrange - Prépare le mock pour retourner une liste vide
        Mockito.`when`(exerciseRepository.getAllExercises()).thenReturn(emptyList())


        // Act - Exécute l'action de test : appelle la méthode execute() du use case
        val result = getAllExercisesUseCase.execute()


        // Assert - Vérifie que le résultat est bien une liste vide
        assertTrue(result.isEmpty())
    }


}