package com.devopsbug.lettergame.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.lettergame.R
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language
import com.devopsbug.lettergame.model.Letter
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageSelectionRow
import com.devopsbug.lettergame.util.LetterGameUtils.getRawResourceId
import com.devopsbug.lettergame.util.LetterGameUtils.playAudio


@Composable
fun RandomLetterScreen(
    currentLanguage: Language = Languages.german,
    currentLetter: Letter,
    updateLanguage: (Language) -> Unit,
    newRandomLetter: () -> Unit
    ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Column (
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxWidth().padding(24.dp)
        ){
            Text(
                text = "How to play:",
                fontSize = 36.sp,
            )
            Text(
                text = "1. Choose a language\n" +
                        "2. Click the letter (make sure audio is on)"
            )
        }
        Row {
            Image(
                painter = painterResource(R.drawable.devopsbug_bug_158x100),
                contentDescription = "Ladybug icon"
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        LanguageSelectionRow(
            currentLanguage = currentLanguage,
            updateLanguage = updateLanguage,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .border(3.dp, MaterialTheme.colorScheme.primaryContainer)

        )
        Spacer(modifier = Modifier.height(16.dp))
        Row (
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            RandomLetterTile(
                letter = currentLetter,
                language = currentLanguage,
                newRandomLetter = newRandomLetter,
                modifier = Modifier
                    .height(248.dp)
                    .width(248.dp)

            )
        }

    }
}

//Function to display letter tile with audio playback when clicked
@Composable
private fun RandomLetterTile(letter: Letter, language: Language, newRandomLetter: () -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Button(
        onClick = {
            val resourceId = getRawResourceId(context, language.audioFilePrefix, letter.letterLiteral.lowercase())
            playAudio(context, resourceId, onCompletion = { newRandomLetter() })
        },
        modifier = modifier,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(1.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Text(
            text = letter.letterLiteral,
            fontSize = 100.sp
        )
    }
}