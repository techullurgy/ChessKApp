package com.techullurgy.chesskapp.screens.game_screen.models

import androidx.compose.runtime.Stable

@Stable
sealed interface UiTurn {
    @Stable
    data class YourTurn(
        val lastOpponentMove: Pair<Int, Int>? = null,
        val selectedIndex: Int? = null,
        val availableMoves: List<Int> = emptyList()
    ): UiTurn

    @Stable
    data object OpponentTurn: UiTurn
}
