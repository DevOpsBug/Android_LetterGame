package com.devopsbug.lettergame.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LetterGameStartScreen(onClickExplore: () -> Unit, onClickLanguage: () -> Unit) {
    Column (
        //modifier = Modifier.padding(paddingValues)
    ){
        Text(text = "Letter Game Start Screen")
        Button(
            onClick = onClickExplore,
            content = { Text(text = "Explore Letters") }
        )
        Button(
            onClick = onClickLanguage,
            content = { Text(text = "Play Letter Game") }
        )
    }

}