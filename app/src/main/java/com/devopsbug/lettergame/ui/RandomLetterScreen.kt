package com.devopsbug.lettergame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageSelectionRow

@Composable
fun RandomLetterScreen(
    currentLanguage: Language = Languages.german,
    updateLanguage: (Language) -> Unit,
    ) {
    Column {
        Text(
            text = "Random Letter Screen"
        )
        Text(
            text = "Selected Language: ${currentLanguage.name}",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LanguageSelectionRow(
            currentLanguage = currentLanguage,
            updateLanguage = updateLanguage
        )
    }

}