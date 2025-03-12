package com.devopsbug.lettergame.ui

import com.devopsbug.lettergame.data.Datasource
import com.devopsbug.lettergame.model.Letter

data class LetterGameUiState(
    val language: Language = Language.ENGLISH,
    val level: Int = 1,
    val currentLetterSet: List<Letter> = Datasource.lettersByLevel[level -1],
    val currentLetter: Letter = currentLetterSet.random(),
)

enum class Language {
    GERMAN,
    ENGLISH,
    ITALIAN
}