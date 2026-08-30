package com.techullurgy.chesskapp.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun ChessKTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if(darkTheme) DarkChessKColors else LightChessKColors
    val theme = ChessKTheme(colors = colors)

    CompositionLocalProvider(
        value = ChessKTheme.LocalChessKTheme provides theme,
        content = content
    )
}

@Immutable
class ChessKTheme internal constructor(
    val colors: ChessKColors = LightChessKColors,
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes()
) {
    companion object {
        val colors: ChessKColors
            @Composable @ReadOnlyComposable
            get() = LocalChessKTheme.current.colors

        val typography: Typography
            @Composable @ReadOnlyComposable
            get() = LocalChessKTheme.current.typography

        val shapes: Shapes
            @Composable @ReadOnlyComposable
            get() = LocalChessKTheme.current.shapes

        val LocalChessKTheme: ProvidableCompositionLocal<ChessKTheme>
            get() = LocalChessKThemeInstance
    }
}

private val LocalChessKThemeInstance = staticCompositionLocalOf { ChessKTheme() }