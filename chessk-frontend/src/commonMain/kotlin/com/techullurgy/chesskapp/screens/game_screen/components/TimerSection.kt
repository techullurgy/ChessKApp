package com.techullurgy.chesskapp.screens.game_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.techullurgy.chesskapp.screens.game_screen.models.GameState

@Composable
fun TimerSection(
    state: GameState.Data
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = state.yourTime.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = state.opponentTime.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}