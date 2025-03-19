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
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageLevelRow
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageSelectionRow
import com.devopsbug.lettergame.util.LetterGameUtils.getRawResourceId
import com.devopsbug.lettergame.util.LetterGameUtils.playAudio


@Composable
fun RandomLetterScreen(
    currentLanguage: Language,
    currentLetter: Letter,
    currentLevel: Int,
    newRandomLetter: () -> Unit
    ) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth(1f)
            .padding(start = 24.dp, end = 24.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LanguageLevelRow(
                currentLanguage = currentLanguage,
                currentLevel = currentLevel
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "How to play:",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "1. Try to say the sound of the letter\n" +
                        "2. Click the letter to hear the correct sound",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Image(
                painter = painterResource(R.drawable.devopsbug_bug_158x100),
                contentDescription = "Ladybug icon",
                modifier = Modifier.fillMaxWidth(fraction = 0.2f)
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(32.dp))
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
        border = BorderStroke(5.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
    ) {
        Text(
            text = letter.letterLiteral,
            fontSize = 100.sp
        )
    }
}