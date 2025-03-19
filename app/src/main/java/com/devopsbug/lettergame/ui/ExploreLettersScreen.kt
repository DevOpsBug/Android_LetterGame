package com.devopsbug.lettergame.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.devopsbug.lettergame.ui.theme.greenButtonColor
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageLevelRow
import com.devopsbug.lettergame.util.LetterGameUtils.getRawResourceId
import com.devopsbug.lettergame.util.LetterGameUtils.playAudio


@Composable
fun ExploreLettersScreen(
    currentLanguage: Language = Languages.german,
    updateLanguage: (Language) -> Unit,
    currentLetterSet: List<Letter> ,
    currentLevel: Int,
    continueToRandomLetterScreen: () -> Unit
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
                text = "Explore the Letters:",
                fontSize = 30.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "1. Click a letters to hear the pronounciation\n" +
                        "2. Click PLAY to start the game",
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
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            userScrollEnabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(color = MaterialTheme.colorScheme.background)
                .border(width = 1.dp, color = Color.DarkGray),
            content = {
                items(currentLetterSet) { letter ->
                    ExploreLetterTile(
                        letter = letter,
                        language = currentLanguage,
                    )
                }
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = continueToRandomLetterScreen,
            colors = ButtonDefaults.buttonColors(
                containerColor =  greenButtonColor
            ),
            border = BorderStroke(width = 2.dp, color = Color.DarkGray),
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text = "PLAY",
                fontSize = 24.sp
            )
        }
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



