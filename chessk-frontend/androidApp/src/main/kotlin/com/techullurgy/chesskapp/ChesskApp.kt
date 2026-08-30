package com.techullurgy.chesskapp

import android.app.Application
import androidx.compose.ui.ComposeUiFlags
import androidx.compose.ui.ExperimentalComposeUiApi

class ChesskApp: Application() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate() {
        ComposeUiFlags.isMediaQueryIntegrationEnabled = true
        super.onCreate()
    }
}