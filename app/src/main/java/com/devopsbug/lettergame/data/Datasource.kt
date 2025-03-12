package com.devopsbug.lettergame.data

import com.devopsbug.lettergame.R
import com.devopsbug.lettergame.model.Letter

object Datasource {
    val lettersLevel1 = listOf(                 //Level 1 = A-M
        Letter(
            letterLiteral = "A",
            letterAudioGerman = R.raw.de_a
        ),
        Letter(
            letterLiteral = "B",
            letterAudioGerman = R.raw.de_b
        ),
        Letter(
            letterLiteral = "C",
            letterAudioGerman = R.raw.de_c
        ),
        Letter(
            letterLiteral = "D",
            letterAudioGerman = R.raw.de_d
        ),
        Letter(
            letterLiteral = "E",
            letterAudioGerman = R.raw.de_e
        ),
        Letter(
            letterLiteral = "F",
            letterAudioGerman = R.raw.de_f
        ),
        Letter(
            letterLiteral = "G",
            letterAudioGerman = R.raw.de_g
        ),
        Letter(
            letterLiteral = "H",
            letterAudioGerman = R.raw.de_h
        ),
        Letter(
            letterLiteral = "I",
            letterAudioGerman = R.raw.de_i
        ),
        Letter(
            letterLiteral = "J",
            letterAudioGerman = R.raw.de_j
        ),
        Letter(
            letterLiteral = "K",
            letterAudioGerman = R.raw.de_k
        ),
        Letter(
            letterLiteral = "L",
            letterAudioGerman = R.raw.de_l
        ),
        Letter(
            letterLiteral = "M",
            letterAudioGerman = R.raw.de_m
        ),
    )
    val lettersLevel2 = lettersLevel1 + listOf(                 //Level 2 = A-Z
        Letter(
            letterLiteral = "N",
            letterAudioGerman = R.raw.de_n
            ),
        Letter(
            letterLiteral = "O",
            letterAudioGerman = R.raw.de_o
        ),
        Letter(
            letterLiteral = "P",
            letterAudioGerman = R.raw.de_p
        ),
        Letter(
            letterLiteral = "Q",
            letterAudioGerman = R.raw.de_q
        ),
        Letter(
            letterLiteral = "R",
            letterAudioGerman = R.raw.de_r
        ),
        Letter(
            letterLiteral = "S",
            letterAudioGerman = R.raw.de_s
        ),
        Letter(
            letterLiteral = "T",
            letterAudioGerman = R.raw.de_t
        ),
        Letter(
            letterLiteral = "U",
            letterAudioGerman = R.raw.de_u
        ),
        Letter(
            letterLiteral = "V",
            letterAudioGerman = R.raw.de_v
        ),
        Letter(
            letterLiteral = "W",
            letterAudioGerman = R.raw.de_w
        ),
        Letter(
            letterLiteral = "X",
            letterAudioGerman = R.raw.de_x
            ),
        Letter(
            letterLiteral = "Y",
            letterAudioGerman = R.raw.de_y
        ),
        Letter(
            letterLiteral = "Z",
            letterAudioGerman = R.raw.de_z
        )
    )
    val lettersLevel3 = lettersLevel2 + listOf(             //Level 3 = A-Z + a-m
        Letter(
            letterLiteral = "a",
            letterAudioGerman = R.raw.de_a
        ),
        Letter(
            letterLiteral = "b",
            letterAudioGerman = R.raw.de_b
        ),
        Letter(
            letterLiteral = "c",
            letterAudioGerman = R.raw.de_c
        ),
        Letter(
            letterLiteral = "d",
            letterAudioGerman = R.raw.de_d
        ),
        Letter(
            letterLiteral = "e",
            letterAudioGerman = R.raw.de_e
        ),
        Letter(
            letterLiteral = "f",
            letterAudioGerman = R.raw.de_f
        ),
        Letter(
            letterLiteral = "g",
            letterAudioGerman = R.raw.de_g
        ),
        Letter(
            letterLiteral = "h",
            letterAudioGerman = R.raw.de_h
        ),
        Letter(
            letterLiteral = "i",
            letterAudioGerman = R.raw.de_i
        ),
        Letter(
            letterLiteral = "j",
            letterAudioGerman = R.raw.de_j
        ),
        Letter(
            letterLiteral = "k",
            letterAudioGerman = R.raw.de_k
        ),
        Letter(
            letterLiteral = "l",
            letterAudioGerman = R.raw.de_l
        ),
        Letter(
            letterLiteral = "m",
            letterAudioGerman = R.raw.de_m
        ),
    )
    val lettersLevel4 = lettersLevel3 + listOf(                 //Level 4 = A-Z + a-z
        Letter(
            letterLiteral = "n",
            letterAudioGerman = R.raw.de_n
        ),
        Letter(
            letterLiteral = "o",
            letterAudioGerman = R.raw.de_o
        ),
        Letter(
            letterLiteral = "p",
            letterAudioGerman = R.raw.de_p
        ),
        Letter(
            letterLiteral = "q",
            letterAudioGerman = R.raw.de_q
        ),
        Letter(
            letterLiteral = "r",
            letterAudioGerman = R.raw.de_r
        ),
        Letter(
            letterLiteral = "s",
            letterAudioGerman = R.raw.de_s
        ),
        Letter(
            letterLiteral = "t",
            letterAudioGerman = R.raw.de_t
        ),
        Letter(
            letterLiteral = "u",
            letterAudioGerman = R.raw.de_u
        ),
        Letter(
            letterLiteral = "v",
            letterAudioGerman = R.raw.de_v
        ),
        Letter(
            letterLiteral = "w",
            letterAudioGerman = R.raw.de_w
        ),
        Letter(
            letterLiteral = "x",
            letterAudioGerman = R.raw.de_x
        ),
        Letter(
            letterLiteral = "y",
            letterAudioGerman = R.raw.de_y
        ),
        Letter(
            letterLiteral = "z",
            letterAudioGerman = R.raw.de_z
        )
    )
    val lettersAll = lettersLevel4
    val lettersByLevel = mutableListOf(
        lettersLevel1,  //A-M
        lettersLevel2,  //A-Z
        lettersLevel3,  //A-Z;a-m
        lettersLevel4   //A-Z;a-z
    )
}