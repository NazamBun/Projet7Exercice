package com.openclassrooms.arista


import com.openclassrooms.arista.data.repository.ExerciseRepository
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.model.ExerciseCategory
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
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

@RunWith(JUnit4::class)
class AddNewExerciseUseCaseTest {

    @Mock
    private lateinit var exerciseRepository: ExerciseRepository

    private lateinit var addNewExerciseUseCase: AddNewExerciseUseCase

    /**
     * - Avant chaque test :
     *     - Créer une version fictive de ExerciseRepository (exerciseRepository)
     *     - Initialiser AddNewExerciseUseCase avec ce repository fictif
     */
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        addNewExerciseUseCase = AddNewExerciseUseCase(exerciseRepository)
    }

    /**
     * - Après chaque test :
     *         - Nettoyer les mocks pour éviter les interférences entre tests
     */
    @After
    fun tearDown() {
        Mockito.framework().clearInlineMocks()
    }

    //- Définir un test : "lorsqu'on ajoute un exercice, le repository doit l'ajouter aussi"
    @Test
    fun `when adding an exercise, repository should add it`() = runBlocking {
        // Arrange
        /**
         * - Préparer les données de test :
         *             - Créer un exercice appelé exerciseToAdd avec les détails suivants :
         *                 - pas d'identifiant (id = null)
         *                 - heure de début (startTime) à maintenant
         *                 - durée de 30 minutes
         *                 - catégorie "Running"
         *                 - intensité de 5
         */
        val exerciseToAdd = Exercise(
            id = null,
            startTime = LocalDateTime.now(),
            duration = 30,
            category = ExerciseCategory.Running,
            intensity = 5
        )
        //- Définir un identifiant utilisateur appelé userId avec la valeur 1
        val userId = 1L

        // Act
        /**
         * - Exécuter l'action du use cas :
         *             - Appeler la fonction d'ajout dans AddNewExerciseUseCase en passant exerciseToAdd et userId
         *
         */
        addNewExerciseUseCase.execute(exerciseToAdd, userId)

        // Assert
        /**
         * - Vérifier le résultat :
         *             - Vérifier que le repository fictif a bien reçu la demande d'ajout pour exerciseToAdd et userId
         */
        Mockito.verify(exerciseRepository).addExercice(exerciseToAdd, userId)
    }
}
