package com.devopsbug.lettergame.data

import androidx.annotation.DrawableRes
import com.devopsbug.lettergame.R
import com.devopsbug.lettergame.model.Language

object Languages {
    val german = Language(
        name = "german",
        audioFilePrefix = "de",
        flagImage = R.drawable.german,
    )
    val english = Language(
        name = "english",
        audioFilePrefix = "en",
        flagImage = R.drawable.english,
    )
    val italian = Language(
        name = "italian",
        audioFilePrefix = "it",
        flagImage = R.drawable.italian,
    )
}