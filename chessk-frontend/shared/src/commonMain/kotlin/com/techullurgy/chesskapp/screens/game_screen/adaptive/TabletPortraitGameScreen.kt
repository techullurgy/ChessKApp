package com.techullurgy.chesskapp.screens.game_screen.adaptive

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techullurgy.chesskapp.core.utils.SquareModifier
import com.techullurgy.chesskapp.screens.game_screen.components.ChessBoard
import com.techullurgy.chesskapp.screens.game_screen.components.InfoBar
import com.techullurgy.chesskapp.screens.game_screen.models.GameState

@Composable internal fun TabletPortraitGameScreen(
    state: GameState.Data
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InfoBar(state, Modifier.fillMaxWidth())

        ChessBoard(
            board = state.board,
            turn = state.turn,
            kingCheckIndices = state.kingCheckIndices,
            modifier = Modifier
                .then(SquareModifier())
        )
    }
}
