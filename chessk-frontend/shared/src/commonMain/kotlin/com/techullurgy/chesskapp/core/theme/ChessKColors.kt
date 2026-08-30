package com.techullurgy.chesskapp.core.theme

import androidx.compose.ui.graphics.Color

class ChessKColors internal constructor(
    val boardWhiteColor: Color,
    val boardBlackColor: Color,
    val boardBorderColor: Color,
    val cellAvailableColor: Color,
    val cellInCheckColor: Color,
    val cellSelectedColor: Color,

    val bgGradient1: Color,
    val bgGradient2: Color,
)

internal val LightChessKColors = ChessKColors(
    boardWhiteColor = Color.hsv(286f, .20f, .93f),
    boardBlackColor = Color.hsv(293f, .63f, .5f),
    boardBorderColor = Color.White,
    cellAvailableColor = Color.hsv(21f, .66f, .87f),
    cellInCheckColor = Color.Red,
    cellSelectedColor = Color.hsv(201f, .66f, .87f),
    bgGradient1 = Color.hsv(80f, .84f,.96f),
    bgGradient2 = Color.hsv(100f, .95f, .75f)
)
internal val DarkChessKColors = ChessKColors(
    boardWhiteColor = Color(203, 177, 135),
    boardBlackColor = Color(0xFFA4725B),
    boardBorderColor = Color.White,
    cellAvailableColor = Color.hsv(21f, .66f, .87f),
    cellInCheckColor = Color.Red,
    cellSelectedColor = Color.hsv(201f, .66f, .87f),
    bgGradient1 = Color.hsv(80f, .84f,.96f),
    bgGradient2 = Color.hsv(100f, .95f, .75f)
)