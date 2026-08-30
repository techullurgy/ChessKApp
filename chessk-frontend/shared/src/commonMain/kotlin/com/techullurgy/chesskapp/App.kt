package com.techullurgy.chesskapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ComposeUiFlags
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chesskapp.shared.generated.resources.Res
import chesskapp.shared.generated.resources.bungee_regular
import com.techullurgy.chesskapp.core.theme.ChessKTheme
import com.techullurgy.chesskapp.core.theme.colors
import com.techullurgy.chesskapp.screens.game_screen.GameScreen
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationStyleApi::class)
@Composable
@PreviewScreenSizes
@Preview(device = Devices.PHONE, )
fun App() {
    if(!ComposeUiFlags.isMediaQueryIntegrationEnabled) {
        ComposeUiFlags.isMediaQueryIntegrationEnabled = true
    }
    Box(
        modifier = Modifier.fillMaxSize().styleable(style = backgroundStyle)
    ) {
        GameScreen()
    }
}

@OptIn(ExperimentalFoundationStyleApi::class)
private val backgroundStyle = Style {
    background(
        Brush.linearGradient(
            colors = listOf(colors.bgGradient1, colors.bgGradient2)
        )
    )
}

//@Preview
@Composable
fun HomePage() {
    val fontFamily = FontFamily(Font(Res.font.bungee_regular))

    val textStyle = LocalTextStyle.current.copy(fontFamily = fontFamily)

    val colors = ChessKTheme.LocalChessKTheme.current.colors

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(listOf(Color.Red, Color.Blue, Color.Yellow, Color.Magenta)))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
        Box {
            Text(
                style = textStyle,
                color = Color.White,
                text = "CHECK MATE IN REAL TIME",
                fontSize = 60.sp
            )
        }

        Spacer(Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .drawBehind {
                    drawRect(
                        color = Color.Black,
                        topLeft = Offset(10.dp.toPx(), 10.dp.toPx()),
                        size = size
                    )
                    drawRect(
                        color = Color.Blue
                    )
                }
                .padding(vertical = 16.dp, horizontal = 24.dp)
        ) {
            Text("START ->", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        }
    }
}