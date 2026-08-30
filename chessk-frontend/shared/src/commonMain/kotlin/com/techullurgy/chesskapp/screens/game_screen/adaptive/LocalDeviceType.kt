package com.techullurgy.chesskapp.screens.game_screen.adaptive

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.techullurgy.chesskapp.core.utils.DeviceConfigurationType

val LocalDeviceType = staticCompositionLocalOf<DeviceConfigurationType> { error("No Value provided for LocalDeviceType") }

@Composable
fun ProvideDeviceType(
    deviceType: DeviceConfigurationType,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalDeviceType provides deviceType,
        content = content
    )
}