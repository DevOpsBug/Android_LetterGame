package com.devopsbug.lettergame.ui

import androidx.lifecycle.ViewModel
import com.devopsbug.lettergame.data.Datasource
import com.devopsbug.lettergame.model.Language
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LetterGameViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LetterGameUiState())
    val uiState: StateFlow<LetterGameUiState> = _uiState.asStateFlow()

    fun updateLanguage(language: Language) {
        _uiState.value = _uiState.value.copy(language = language)
    }

    fun updateLevel(level: Int) {
        val currentLetterSet = Datasource.lettersByLevel[level -1]
        _uiState.value = _uiState.value.copy(
            level = level,
            currentLetterSet = currentLetterSet,
            currentLetter = currentLetterSet.random()
        )
    }

    fun newLetter() {
        _uiState.value = _uiState.value.copy(
            currentLetter = _uiState.value.currentLetterSet.random()
        )
    }
}