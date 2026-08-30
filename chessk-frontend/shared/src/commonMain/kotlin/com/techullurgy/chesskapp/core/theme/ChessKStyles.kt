@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.techullurgy.chesskapp.core.theme

import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.StyleScope
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography

val StyleScope.colors: ChessKColors
    get() = ChessKTheme.LocalChessKTheme.currentValue.colors

val StyleScope.typography: Typography
    get() = ChessKTheme.LocalChessKTheme.currentValue.typography

val StyleScope.shapes: Shapes
    get() = ChessKTheme.LocalChessKTheme.currentValue.shapes