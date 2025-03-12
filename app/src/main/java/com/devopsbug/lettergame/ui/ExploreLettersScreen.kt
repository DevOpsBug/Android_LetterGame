package com.devopsbug.lettergame.ui

import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devopsbug.lettergame.data.Datasource
import com.devopsbug.lettergame.model.Letter
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.devopsbug.lettergame.R


@Composable
fun ExploreLettersScreen(currentLanguage: Language = Language.GERMAN, updateLanguage: (Language) -> Unit, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Explore Letters Screen",
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
        Text(
            text = "Selected Language: ${currentLanguage.name}",
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Column (modifier = Modifier.fillMaxWidth()){
            Text(text = "Choose Language")
            Row {
                Button(
                    onClick = { updateLanguage(Language.GERMAN) },
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.DarkGray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.2f)
                        .weight(1f),
                    ) {
                    Image(
                        painter = painterResource(R.drawable.german),
                        contentDescription = null,
                    )
                }
                Button(
                    onClick = { updateLanguage(Language.ITALIAN) },
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.DarkGray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.weight(1f),
                ) {
                    Image(
                        painter = painterResource(R.drawable.italian),
                        contentDescription = null,
                    )
                }
                Button(
                    onClick = { updateLanguage(Language.ENGLISH) },
                    shape = RectangleShape,
                    border = BorderStroke(1.dp, Color.DarkGray),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    ),
                    modifier = Modifier.weight(1f),
                ) {
                    Image(
                        painter = painterResource(R.drawable.english),
                        contentDescription = null,
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(6),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(Datasource.lettersLevel4.size) { index ->
                    LetterTile(letter = Datasource.lettersLevel4[index], onClick = {})
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
        )
    }

}

@Composable
fun LetterTile(letter: Letter, onClick: () -> Unit) {
    val context = LocalContext.current
    var mediaPlayer = MediaPlayer.create(context, letter.letterAudioGerman)
    Button(
        onClick = {
            mediaPlayer.start()
        },
        modifier = Modifier,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(1.dp, Color.DarkGray),
        contentPadding = PaddingValues(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(
            text = letter.letterLiteral
        )
    }
}