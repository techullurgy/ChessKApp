package com.techullurgy.chesskapp.screens.game_screen

import androidx.lifecycle.ViewModel
import com.techullurgy.chesskapp.screens.game_screen.models.GameState
import com.techullurgy.chesskapp.screens.game_screen.models.GameState.Data
import com.techullurgy.chesskapp.screens.game_screen.models.Initial
import com.techullurgy.chesskapp.screens.game_screen.models.UiBoard
import com.techullurgy.chesskapp.screens.game_screen.models.UiParticipant
import com.techullurgy.chesskapp.screens.game_screen.models.UiPiece
import com.techullurgy.chesskapp.screens.game_screen.models.UiPieceColor
import com.techullurgy.chesskapp.screens.game_screen.models.UiTurn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class GameViewModel: ViewModel() {
    val state: StateFlow<GameState> field = MutableStateFlow<GameState>(GameState.Loading)

    init {
        state.value = Data(
            you = UiParticipant("Irsath", UiPieceColor.White),
            other = UiParticipant("Riyas", UiPieceColor.Black),
            board = initialBoard(),
            assignedColor = UiPieceColor.White,
            turn = UiTurn.YourTurn(),
//            turn = UiTurn.YourTurn(
//                selectedIndex = 62,
//                availableMoves = listOf(6, 14, 22, 30, 38, 46, 54, 63, 61, 60, 59, 58, 57, 56)
//            ),
            kingCheckIndices = listOf(),
            yourTime = 10.minutes.inWholeMilliseconds,
            opponentTime = (8.minutes + 36.seconds).inWholeMilliseconds
        )
    }
}

private fun initialBoard() = UiBoard.Initial

private fun Take1() = UiBoard(
    pieces = List(8) { r ->
        List(8) { c ->
            when {
                (r == 0 && c == 6) -> UiPiece.King(UiPieceColor.Black)
                (r == 1 && c == 5) -> UiPiece.Sepoy(UiPieceColor.Black)
                (r == 1 && c == 7) -> UiPiece.Sepoy(UiPieceColor.Black)
                (r == 2 && c == 5) -> UiPiece.Knight(UiPieceColor.White)
                (r == 2 && c == 7) -> UiPiece.Queen(UiPieceColor.White)
                (r == 4 && c == 7) -> UiPiece.Sepoy(UiPieceColor.White)
                (r == 5 && c == 4) -> UiPiece.Sepoy(UiPieceColor.White)
                (r == 6 && c == 3) -> UiPiece.Sepoy(UiPieceColor.White)
                (r == 7 && c == 6) -> UiPiece.Rook(UiPieceColor.White)
                else -> null
            }
        }
    },
    cutPieces = emptyList()
)