package com.techullurgy.chesskapp.screens.game_screen.adaptive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.techullurgy.chesskapp.screens.game_screen.components.ChessBoard
import com.techullurgy.chesskapp.screens.game_screen.components.PlayerInfoSection
import com.techullurgy.chesskapp.screens.game_screen.components.TimerSection
import com.techullurgy.chesskapp.core.utils.SquareModifier
import com.techullurgy.chesskapp.screens.game_screen.models.GameState

@Composable
internal fun PhonePortraitGameScreen(
    state: GameState.Data
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PlayerInfoSection(state)
        TimerSection(state)

        ChessBoard(
            board = state.board,
            turn = state.turn,
            kingCheckIndices = state.kingCheckIndices,
            modifier = Modifier
                .padding(vertical = 40.dp)
                .then(SquareModifier())
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(0.dp, Alignment.End),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {},
            ) {
                Text("Complete Move")
            }
        }
    }
}