package com.techullurgy.chesskapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ComposeUiFlags
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import chesskapp.shared.generated.resources.Res
import chesskapp.shared.generated.resources.bungee_regular
import com.techullurgy.chesskapp.core.theme.colors
import com.techullurgy.chesskapp.screens.game_screen.GameScreen
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationStyleApi::class)
@Composable
//@PreviewScreenSizes
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

@Preview
@Composable
fun HomePage() {
    val fontFamily = FontFamily(Font(Res.font.bungee_regular))

    val textStyle = LocalTextStyle.current.copy(fontFamily = fontFamily)

    Column(
        modifier = Modifier.padding(32.dp)
    ) {
        Box {
            Text(
                style = textStyle,
                color = Color(189, 212, 156),
                text = "CHECKMATE IN REAL TIME",
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