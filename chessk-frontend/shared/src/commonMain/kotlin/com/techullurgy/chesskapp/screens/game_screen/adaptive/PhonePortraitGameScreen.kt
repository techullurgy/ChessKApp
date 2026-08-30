package com.techullurgy.chesskapp.screens.game_screen.adaptive

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.chesskapp.core.utils.SquareModifier
import com.techullurgy.chesskapp.screens.game_screen.components.ChessBoard
import com.techullurgy.chesskapp.screens.game_screen.components.InfoBar
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
        Box(
            modifier = Modifier.fillMaxWidth().height(100.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = "CHESSK",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.ExtraBold,
                    style = LocalTextStyle.current.copy(brush = Brush.linearGradient(listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow, Color.Magenta)))
                )
            }
        }

        InfoBar(
            state = state,
            modifier = Modifier
                .fillMaxWidth().height(200.dp)
        )

        ChessBoard(
            board = state.board,
            turn = state.turn,
            kingCheckIndices = state.kingCheckIndices,
            modifier = Modifier
                .then(SquareModifier())
        )
    }
}