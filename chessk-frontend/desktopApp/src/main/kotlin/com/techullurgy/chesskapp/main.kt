package com.techullurgy.chesskapp

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.LocalUiMediaScope
import androidx.compose.ui.UiMediaScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

@OptIn(ExperimentalMediaQueryApi::class)
fun main() = application {
    val state = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        title = "ChessKApp",
        state = state
    ) {
        val scope = remember(state.size) {
            object: UiMediaScope {
                override val windowPosture: UiMediaScope.Posture get() = UiMediaScope.Posture.Flat
                override val windowWidth: Dp get() = state.size.width
                override val windowHeight: Dp get() = state.size.height
                override val pointerPrecision: UiMediaScope.PointerPrecision get() = UiMediaScope.PointerPrecision.Fine
                override val keyboardKind: UiMediaScope.KeyboardKind get() = UiMediaScope.KeyboardKind.Physical
                override val hasMicrophone: Boolean get() = false
                override val hasCamera: Boolean get() = false
                override val viewingDistance: UiMediaScope.ViewingDistance get() = UiMediaScope.ViewingDistance.Near
            }
        }
        CompositionLocalProvider(
            LocalUiMediaScope provides scope
        ) {
            App()
        }
    }
}