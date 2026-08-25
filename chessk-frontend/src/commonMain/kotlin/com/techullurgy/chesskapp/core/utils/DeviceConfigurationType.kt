package com.techullurgy.chesskapp.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalMediaQueryApi
import androidx.compose.ui.derivedMediaQuery
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

enum class DeviceConfigurationType {
    PhonePortrait,
    PhoneLandscape,
    TabletPortrait,
    TabletLandscape,
    Desktop
}

@OptIn(ExperimentalMediaQueryApi::class)
@Composable
fun currentDeviceConfigurationType(): DeviceConfigurationType? {

//    WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND // 600
//    WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND // 830
//    WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND // 1200
//    WindowSizeClass.WIDTH_DP_EXTRA_LARGE_LOWER_BOUND // 1600
//
//    WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND // 480
//    WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND // 900


    val isPhonePortrait by derivedMediaQuery {
        windowWidth < WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND.dp
    }

    val isPhoneLandscape by derivedMediaQuery {
        windowWidth >= WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND.dp &&
                windowHeight < WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND.dp
    }

    val isTabletPortrait by derivedMediaQuery {
        windowWidth >= WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND.dp &&
                windowWidth < WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND.dp &&
                windowHeight >= WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND.dp
    }

    val isTabletLandscape by derivedMediaQuery {
        windowWidth >= WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND.dp &&
                windowWidth < WindowSizeClass.WIDTH_DP_EXTRA_LARGE_LOWER_BOUND.dp
    }

    val isDesktop by derivedMediaQuery {
        windowWidth >= WindowSizeClass.WIDTH_DP_EXTRA_LARGE_LOWER_BOUND.dp
    }

    return when {
        isPhonePortrait -> DeviceConfigurationType.PhonePortrait
        isPhoneLandscape -> DeviceConfigurationType.PhoneLandscape
        isTabletPortrait -> DeviceConfigurationType.TabletPortrait
        isTabletLandscape -> DeviceConfigurationType.TabletLandscape
        isDesktop -> DeviceConfigurationType.Desktop
        else -> null
    }
}