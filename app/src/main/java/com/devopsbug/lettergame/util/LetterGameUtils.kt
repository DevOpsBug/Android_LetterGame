package com.devopsbug.lettergame.util

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language

object LetterGameUtils {
    fun getRawResourceId(context: Context, audioFilePrefix: String, letter: String): Int {
        val resourceName = "${audioFilePrefix}_$letter"
        return context.resources.getIdentifier(resourceName, "raw", context.packageName)
    }

    // Function to play the audio
    fun playAudio(context: Context, resourceId: Int) {
        try {
            val mediaPlayer = MediaPlayer.create(context, resourceId)
            mediaPlayer?.start()

            mediaPlayer?.setOnCompletionListener {
                it.release()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    //function to display language selection row
    @Composable
    fun LanguageSelectionRow(
        currentLanguage: Language = Languages.german,
        updateLanguage: (Language) -> Unit,
    ){
        Column (modifier = Modifier.fillMaxWidth()){
            Text(text = "Choose Language")
            Row {
                listOf(Languages.german, Languages.italian, Languages.english).forEach { language ->

                    Button(
                        onClick = { updateLanguage(language) },
                        shape = RectangleShape,
                        border = BorderStroke(1.dp, Color.DarkGray),
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