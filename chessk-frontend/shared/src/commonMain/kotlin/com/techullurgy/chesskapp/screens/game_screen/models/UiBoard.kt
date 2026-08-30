package com.techullurgy.chesskapp.screens.game_screen.models

import androidx.compose.runtime.Stable

@Stable
data class UiBoard(
    val pieces: List<List<UiPiece?>>,
    val cutPieces: List<UiPiece>
) {
    companion object
}

val UiBoard.Companion.Initial get() = UiBoard(
    pieces = List(8) { r ->
        List(8) { c ->
            when (r) {
                0 -> {
                    when (c) {
                        0, 7 -> UiPiece.Rook(UiPieceColor.White)
                        1, 6 -> UiPiece.Knight(UiPieceColor.White)
                        2, 5 -> UiPiece.Bishop(UiPieceColor.White)
                        3 -> UiPiece.King(UiPieceColor.White)
                        4 -> UiPiece.Queen(UiPieceColor.White)
                        else -> TODO()
                    }
                }

                1 -> UiPiece.Sepoy(UiPieceColor.White)
                6 -> UiPiece.Sepoy(UiPieceColor.Black)
                7 -> {
                    when (c) {
                        0, 7 -> UiPiece.Rook(UiPieceColor.Black)
                        1, 6 -> UiPiece.Knight(UiPieceColor.Black)
                        2, 5 -> UiPiece.Bishop(UiPieceColor.Black)
                        3 -> UiPiece.King(UiPieceColor.Black)
                        4 -> UiPiece.Queen(UiPieceColor.Black)
                        else -> TODO()
                    }
                }

                else -> null
            }
        }
    },
    cutPieces = emptyList()
)