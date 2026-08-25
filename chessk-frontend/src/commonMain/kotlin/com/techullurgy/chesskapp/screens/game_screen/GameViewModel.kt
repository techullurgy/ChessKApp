package com.techullurgy.chesskapp.screens.game_screen

import androidx.lifecycle.ViewModel
import com.techullurgy.chesskapp.screens.game_screen.models.GameState
import com.techullurgy.chesskapp.screens.game_screen.models.UiTurn
import com.techullurgy.chesskapp.screens.game_screen.models.UiBoard
import com.techullurgy.chesskapp.screens.game_screen.models.UiParticipant
import com.techullurgy.chesskapp.screens.game_screen.models.UiPiece
import com.techullurgy.chesskapp.screens.game_screen.models.UiPieceColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel: ViewModel() {
    val state: StateFlow<GameState> field = MutableStateFlow<GameState>(GameState.Loading)

    init {
        state.value = GameState.Data(
            you = UiParticipant("Irsath", UiPieceColor.White),
            other = UiParticipant("Riyas", UiPieceColor.Black),
            board = initialBoard(),
            assignedColor = UiPieceColor.White,
            turn = UiTurn.YourTurn(
                selectedIndex = 57,
                availableMoves = listOf(51, 42, 40)
            ),
            kingCheckIndices = listOf(59),
            yourTime = 28939283,
            opponentTime = 67723647
        )
    }
}

private fun initialBoard() = UiBoard(
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