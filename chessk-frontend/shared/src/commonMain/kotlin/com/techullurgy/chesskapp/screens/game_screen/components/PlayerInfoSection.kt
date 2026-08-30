package com.techullurgy.chesskapp.screens.game_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techullurgy.chesskapp.screens.game_screen.models.GameState
import com.techullurgy.chesskapp.screens.game_screen.models.UiPieceColor

@Composable
fun PlayerInfoSection(
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
            // Image of Player1
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )
            // Player1 Assigned Color
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        when(state.you.assignedColor) {
                            UiPieceColor.White -> Color.White
                            UiPieceColor.Black -> Color.Black
                        }
                    )
            )

            Text(state.you.name)
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            // Image of Player2
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color.Blue)
            )

            // Player2 Assigned Color
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(
                        when(state.other.assignedColor) {
                            UiPieceColor.White -> Color.White
                            UiPieceColor.Black -> Color.Black
                        }
                    )
            )

            Text(state.other.name)
        }
    }
}