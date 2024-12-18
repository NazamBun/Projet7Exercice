package com.openclassrooms.arista.ui.sleep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openclassrooms.arista.domain.model.Sleep
import com.openclassrooms.arista.domain.usecase.GetAllSleepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SleepViewModel @Inject constructor(private val getAllSleepsUseCase: GetAllSleepsUseCase) :
    ViewModel() {
    // StateFlow pour stocker et observer la liste des sessions de sommeil
    private val _sleeps = MutableStateFlow<List<Sleep>>(emptyList())
    val sleeps: StateFlow<List<Sleep>> = _sleeps.asStateFlow()

    private val _errorFlow = MutableStateFlow<String?>(null)
    val errorFlow: StateFlow<String?> get() = _errorFlow

    init {
        fetchSleeps() // Charge les données de sommeil dès l'initialisation
    }

    fun fetchSleeps() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val sleepList = getAllSleepsUseCase.execute()
                _sleeps.value = sleepList // Met à jour le flux pour notifier l'UI
            } catch (e: Exception) {
                _errorFlow.value = e.message
            }

        }
    }
}
