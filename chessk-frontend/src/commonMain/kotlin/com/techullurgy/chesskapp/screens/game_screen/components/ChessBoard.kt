package com.techullurgy.chesskapp.screens.game_screen.components

import androidx.compose.foundation.layout.ExperimentalGridApi
import androidx.compose.foundation.layout.Grid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techullurgy.chesskapp.screens.game_screen.models.UiBoard
import com.techullurgy.chesskapp.screens.game_screen.models.UiTurn
import com.techullurgy.chesskapp.core.theme.ChessKTheme

internal const val BOARD_SIZE_COUNT = 8 // 8x8

@OptIn(ExperimentalGridApi::class)
@Composable
fun ChessBoard(
    board: UiBoard,
    turn: UiTurn,
    kingCheckIndices: List<Int>,
    modifier: Modifier = Modifier
) {
    val blackColor = ChessKTheme.LocalChessKTheme.current.colors.boardBlackColor
    val whiteColor = ChessKTheme.LocalChessKTheme.current.colors.boardWhiteColor

    Grid(
        config = {
            repeat(BOARD_SIZE_COUNT) {
                row(1.fr)
            }
            repeat(BOARD_SIZE_COUNT) {
                column(1.fr)
            }
        },
        modifier = modifier
    ) {
        (0 until BOARD_SIZE_COUNT*BOARD_SIZE_COUNT).forEach { index ->
            val row = index / BOARD_SIZE_COUNT
            val col = index % BOARD_SIZE_COUNT
            val currentColor = if(row % 2 == 0) {
                if(col % 2 == 0) blackColor else whiteColor
            } else {
                if(col % 2 == 0) whiteColor else blackColor
            }

            val piece = board.pieces[row][col]

            val cellState = provideCellState(index, turn, kingCheckIndices)

            BoardCell(
                cellColor = currentColor,
                piece = piece,
                cellState = cellState
            )
        }
    }
}

private fun provideCellState(
    index: Int,
    turn: UiTurn,
    kingCheckIndices: List<Int>,
): CellState {
    var cellState = CellState.Normal

    when {
        turn is UiTurn.YourTurn -> {
            if(turn.availableMoves.isNotEmpty()) {
                if(turn.availableMoves.contains(index)) {
                    cellState = CellState.AvailableMove
                }
            }

            if(turn.selectedIndex != null) {
                if(index == turn.selectedIndex) {
                    cellState = CellState.Selected
                }
            }
        }
    }

    if(kingCheckIndices.contains(index)) {
        cellState = CellState.InCheck
    }

    return cellState
}