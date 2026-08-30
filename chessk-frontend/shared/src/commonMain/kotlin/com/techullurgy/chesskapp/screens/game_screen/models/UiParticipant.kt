package com.techullurgy.chesskapp.screens.game_screen.models

data class UiParticipant(
    val name: String,
    val assignedColor: UiPieceColor,
    val dpUrl: String? = null
)