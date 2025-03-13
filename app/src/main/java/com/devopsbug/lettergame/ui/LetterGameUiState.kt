package com.devopsbug.lettergame.ui

import com.devopsbug.lettergame.data.Datasource
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language
import com.devopsbug.lettergame.model.Letter

data class LetterGameUiState(
    val language: Language = Languages.english,
    val level: Int = 1,
    val currentLetterSet: List<Letter> = Datasource.lettersByLevel[level -1],
    val currentLetter: Letter = currentLetterSet.random(),
)

