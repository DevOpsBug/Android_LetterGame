package com.devopsbug.lettergame.util

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language
import com.devopsbug.lettergame.model.Letter

object LetterGameUtils {
    fun getRawResourceId(context: Context, audioFilePrefix: String, letter: String): Int {
        val resourceName = "${audioFilePrefix}_$letter"
        return context.resources.getIdentifier(resourceName, "raw", context.packageName)
    }

    fun playAudio(context: Context, resourceId: Int, onCompletion: () -> Unit = {}) {
        try {
            val mediaPlayer = MediaPlayer.create(context, resourceId)
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.release()
                onCompletion()
            }
            mediaPlayer.start()
        } catch (e: Exception) {
            Log.e("LetterGameUtils", "Error playing audio", e)
            onCompletion()
        }
    }

    @Composable
    fun LanguageLevelRow(
        currentLanguage: Language,
        currentLevel: Int
    ){
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                //.padding(start = 24.dp, end = 24.dp)

        ){
            Image(
                painter = painterResource(currentLanguage.flagImage),
                contentDescription = null,
                modifier = Modifier.border(width = 1.dp, color = Color.DarkGray)
            )
            Text(
                text = "Level $currentLevel",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primaryContainer,
                //modifier = Modifier.border(width = 1.dp, color = Color.DarkGray)

            )
        }
    }

    //function to display language selection row
    @Composable
    fun LanguageSelectionRow(
        currentLanguage: Language = Languages.german,
        updateLanguage: (Language) -> Unit,
        modifier: Modifier = Modifier.fillMaxWidth()
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ){
            //Text(text = "Choose Language")
            Row {
                listOf(Languages.german, Languages.italian, Languages.english).forEach { language ->

                    Button(
                        onClick = { updateLanguage(language) },
                        shape = RectangleShape,
                        //border = BorderStroke(5.dp, MaterialTheme.colorScheme.primaryContainer),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (currentLanguage == language) Color.LightGray else Color.White,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        modifier = Modifier
                            .fillMaxWidth(fraction = 0.2f)
                            .weight(1f),
                    ) {
                        Image(
                            painter = painterResource(language.flagImage),
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}