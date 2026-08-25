package com.techullurgy.chesskapp.screens.game_screen.models

import androidx.compose.runtime.Stable

@Stable
data class UiBoard(
    val pieces: List<List<UiPiece?>>,
    val cutPieces: List<UiPiece>
)
