package com.openclassrooms.arista.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.MainApplication
import com.openclassrooms.arista.domain.model.Exercise
import com.openclassrooms.arista.domain.usecase.AddNewExerciseUseCase
import com.openclassrooms.arista.domain.usecase.DeleteExerciseUseCase
import com.openclassrooms.arista.domain.usecase.GetAllExercisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(
    private val getAllExercisesUseCase: GetAllExercisesUseCase,
    private val addNewExerciseUseCase: AddNewExerciseUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {

    // StateFlow pour stocker et observer la liste des exercices
    private val _exercisesFlow = MutableStateFlow<List<Exercise>>(emptyList())
    val exercisesFlow: StateFlow<List<Exercise>> = _exercisesFlow.asStateFlow()

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> get() = _errorFlow

    init {
        loadAllExercises()
    }

    /**
     * Supprime un exercice en appelant le use case de manière asynchrone.
     * Après suppression, recharge la liste des exercices pour mettre à jour l'UI.
     *
     * @param exercise L'exercice à supprimer.
     */
    fun deleteExercise(exercise: Exercise) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                deleteExerciseUseCase.execute(exercise)
                loadAllExercises()
            }
        } catch (e: Exception) {
            _errorFlow.value = e.message
        }
    }

    /**
     * Charge tous les exercices en appelant le use case de manière asynchrone.
     * Met à jour le StateFlow pour notifier l'UI.
     */
    private fun loadAllExercises() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val exercises = getAllExercisesUseCase.execute()
                _exercisesFlow.value = exercises
            } catch (e: Exception) {
                _errorFlow.value = e.message
            }
        }
    }

    /**
     * Ajoute un nouvel exercice en appelant le use case de manière asynchrone.
     * Après ajout, recharge la liste des exercices pour mettre à jour l'UI.
     *
     * @param exercise L'exercice à ajouter.
     */
    fun addNewExercise(exercise: Exercise, userId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addNewExerciseUseCase.execute(exercise, MainApplication.USER_ID)
                loadAllExercises()
            } catch (e: Exception) {
                _errorFlow.value = e.message
            }
        }
    }
}
