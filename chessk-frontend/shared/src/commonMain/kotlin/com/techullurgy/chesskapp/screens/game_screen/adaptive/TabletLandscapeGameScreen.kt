package com.techullurgy.chesskapp.screens.game_screen.adaptive

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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


@Composable internal fun TabletLandscapeGameScreen(
    state: GameState.Data
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f).fillMaxHeight()
        ) {
            InfoBar(state, Modifier.fillMaxWidth())
        }
        Box(
            modifier = Modifier.weight(3f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            ChessBoard(
                board = state.board,
                turn = state.turn,
                kingCheckIndices = state.kingCheckIndices,
                modifier = Modifier.then(SquareModifier())
            )
        }
    }
}