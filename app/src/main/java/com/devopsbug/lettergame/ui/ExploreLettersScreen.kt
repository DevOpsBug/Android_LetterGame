package com.devopsbug.lettergame.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.devopsbug.lettergame.data.Datasource
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language
import com.devopsbug.lettergame.model.Letter
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageSelectionRow
import com.devopsbug.lettergame.util.LetterGameUtils.getRawResourceId
import com.devopsbug.lettergame.util.LetterGameUtils.playAudio


@Composable
fun ExploreLettersScreen(
    currentLanguage: Language = Languages.german,
    updateLanguage: (Language) -> Unit,
    ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){

        Spacer(modifier = Modifier.height(16.dp))
        LanguageSelectionRow(
            currentLanguage = currentLanguage,
            updateLanguage = updateLanguage,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(3.dp, MaterialTheme.colorScheme.primaryContainer)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(Datasource.lettersLevel4.size) { index ->
                    ExploreLetterTile(
                        letter = Datasource.lettersLevel4[index],
                        language = currentLanguage,

                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}

//Function to display letter tile with audio playback when clicked
@Composable
private fun ExploreLetterTile(letter: Letter, language: Language, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    //   val mediaPlayer = MediaPlayer.create(context, letter.letterAudioGerman)
    Button(
        onClick = {
            val resourceId = getRawResourceId(context, language.audioFilePrefix, letter.letterLiteral.lowercase())
            playAudio(context, resourceId)
        },
        modifier = modifier,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(1.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Text(
            text = letter.letterLiteral
        )
    }
}



