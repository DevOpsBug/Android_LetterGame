package com.devopsbug.lettergame.model

import androidx.annotation.RawRes

data class Letter(
    val letterLiteral: String,
    @RawRes val letterAudioGerman: Int
)
