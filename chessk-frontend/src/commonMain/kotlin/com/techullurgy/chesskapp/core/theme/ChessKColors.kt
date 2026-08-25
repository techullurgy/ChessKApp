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
    boardWhiteColor = Color(203, 177, 135),
    boardBlackColor = Color(0xFFA4725B),
    boardBorderColor = Color.White,
    cellAvailableColor = Color.Magenta,
    cellInCheckColor = Color.Red,
    cellSelectedColor = Color.Blue,
    bgGradient1 = Color(0xFFFF0F7B),
    bgGradient2 = Color(0xFFF89B29)
)
internal val DarkChessKColors = ChessKColors(
    boardWhiteColor = Color(203, 177, 135),
    boardBlackColor = Color(0xFFA4725B),
    boardBorderColor = Color.White,
    cellAvailableColor = Color.Magenta,
    cellInCheckColor = Color.Red,
    cellSelectedColor = Color.Blue,
    bgGradient1 = Color(0xFFFF0F7B),
    bgGradient2 = Color(0xFFF89B29)
)