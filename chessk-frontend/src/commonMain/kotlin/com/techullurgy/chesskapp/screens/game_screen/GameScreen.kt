package com.techullurgy.chesskapp.screens.game_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techullurgy.chesskapp.core.utils.DeviceConfigurationType
import com.techullurgy.chesskapp.core.utils.currentDeviceConfigurationType
import com.techullurgy.chesskapp.screens.game_screen.adaptive.DesktopGameScreen
import com.techullurgy.chesskapp.screens.game_screen.adaptive.PhoneLandscapeGameScreen
import com.techullurgy.chesskapp.screens.game_screen.adaptive.PhonePortraitGameScreen
import com.techullurgy.chesskapp.screens.game_screen.adaptive.TabletLandscapeGameScreen
import com.techullurgy.chesskapp.screens.game_screen.adaptive.TabletPortraitGameScreen
import com.techullurgy.chesskapp.screens.game_screen.models.GameState

@OptIn(ExperimentalMediaQueryApi::class)
@Composable
fun GameScreen() {
    val viewModel = viewModel { GameViewModel() }

    val state by viewModel.state.collectAsStateWithLifecycle()

    val deviceType = currentDeviceConfigurationType()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {
        when(state) {
            GameState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is GameState.Data -> {
                val dataState = state as GameState.Data
                when(deviceType) {
                    DeviceConfigurationType.PhonePortrait -> PhonePortraitGameScreen(dataState)
                    DeviceConfigurationType.PhoneLandscape -> PhoneLandscapeGameScreen(dataState)
                    DeviceConfigurationType.TabletPortrait -> TabletPortraitGameScreen(dataState)
                    DeviceConfigurationType.TabletLandscape -> TabletLandscapeGameScreen(dataState)
                    DeviceConfigurationType.Desktop -> DesktopGameScreen(dataState)
                    null -> PhonePortraitGameScreen(dataState)
                }
            }
        }
    }
}