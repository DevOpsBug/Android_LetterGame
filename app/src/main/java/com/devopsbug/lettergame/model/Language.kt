package com.devopsbug.lettergame.model

import androidx.annotation.DrawableRes

data class Language(
    val name: String,
    val audioFilePrefix: String,
    @DrawableRes val flagImage: Int,
)
