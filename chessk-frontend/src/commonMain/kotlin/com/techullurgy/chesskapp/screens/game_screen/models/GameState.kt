package com.techullurgy.chesskapp.screens.game_screen.models

import com.techullurgy.chesskapp.screens.game_screen.components.BOARD_SIZE_COUNT

sealed interface GameState {
    data object Loading: GameState

    data class Data(
        val you: UiParticipant,
        val other: UiParticipant,
        val board: UiBoard,
        val assignedColor: UiPieceColor,
        val turn: UiTurn,
        val kingCheckIndices: List<Int>,
        val yourTime: Long,
        val opponentTime: Long,
    ): GameState {
        val isYourTurn = turn is UiTurn.YourTurn

        init {
            check(
                kingCheckIndices.all { board.pieces[it/BOARD_SIZE_COUNT][it%BOARD_SIZE_COUNT] is UiPiece.King }
            ) {
                "King Check Indices Failed (IllegalState)"
            }
        }
    }
}