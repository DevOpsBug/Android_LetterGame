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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devopsbug.lettergame.R
import com.devopsbug.lettergame.data.Languages
import com.devopsbug.lettergame.model.Language
import com.devopsbug.lettergame.ui.theme.greenButtonColor
import com.devopsbug.lettergame.ui.theme.primaryLightMediumContrast
import com.devopsbug.lettergame.util.LetterGameUtils.LanguageSelectionRow


@Composable
fun LetterGameStartScreen(
    currentLanguage: Language,
    updateLanguage: (Language) -> Unit,
    currentLevel: Int,
    updateLevel: (Int) -> Unit,
    onClickExplore: () -> Unit,
) {
    Column (
        //modifier = Modifier.padding(paddingValues)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(start = 24.dp, end = 24.dp)
                //.border(width = 1.dp, color = Color.DarkGray)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(24.dp)
            ) {
                Text(
                    text = "How to start:",
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "1. Choose a language\n" +
                           "2. Choose a level\n" +
                           "3. Click START to begin",
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Image(
                    painter = painterResource(R.drawable.devopsbug_bug_158x100),
                    contentDescription = "Ladybug icon",
                    //modifier = Modifier.padding(start = 24.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(16.dp))
            LanguageSelectionRow(
                currentLanguage = currentLanguage,
                updateLanguage = updateLanguage,
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(start = 24.dp, end = 24.dp)
                    .border(3.dp, MaterialTheme.colorScheme.primaryContainer)

            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    //.border(1.dp, Color.DarkGray),
            ){
                listOf(1, 2, 3, 4).forEach {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
                    ) {
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            onClick = { updateLevel(it) },
                            contentPadding = PaddingValues(16.dp),
                            border = BorderStroke(width = 2.dp, color = Color.DarkGray),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (currentLevel == it) {
                                    primaryLightMediumContrast
                                } else {
                                    ButtonDefaults.buttonColors().containerColor
                                }
                            )
                        ) {
                            Text(
                                text = "Level $it",
                                fontSize = 24.sp
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = when (it) {
                                1 -> "[A-M]"
                                2 -> "[A-Z}"
                                3 -> "[A-Z; a-m]"
                                else -> "[A-Z; a-z]"
                            },
                            fontSize = 24.sp
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(0.8f).padding(8.dp)
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = onClickExplore,
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  greenButtonColor
                        ),
                        border = BorderStroke(width = 2.dp, color = Color.DarkGray),
                        contentPadding = PaddingValues(16.dp),
                        modifier = Modifier.fillMaxWidth()
                        ){
                            Text(
                                text = "START",
                                fontSize = 24.sp
                            )
                        }
                    }
            }
        }
    }

}