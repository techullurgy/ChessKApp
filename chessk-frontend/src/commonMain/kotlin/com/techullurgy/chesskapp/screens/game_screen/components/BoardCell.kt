@file:OptIn(ExperimentalFoundationStyleApi::class)

package com.techullurgy.chesskapp.screens.game_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.MutableStyleState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleScope
import androidx.compose.foundation.style.StyleStateKey
import androidx.compose.foundation.style.styleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.techullurgy.chesskapp.screens.game_screen.models.UiPiece
import com.techullurgy.chesskapp.core.theme.colors
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun BoardCell(
    cellColor: Color,
    piece: UiPiece?,
    cellState: CellState,
) {
    CompositionLocalProvider(LocalCellColor provides cellColor) {
        val styleState = remember { MutableStyleState(null) }
        styleState.cellState = cellState

        Box(
            modifier = Modifier
                .fillMaxSize()
                .styleable(styleState, cellStyle),
            contentAlignment = Alignment.Center
        ) {
            piece?.let {
                Image(
                    painter = painterResource(it.icon),
                    contentDescription = null,
                    modifier = Modifier.matchParentSize()
                )
            }
        }
    }
}

private val LocalCellColor = compositionLocalOf<Color> { error("No Value for LocalCellColor") }


internal enum class CellState {
    Normal, Selected, InCheck, AvailableMove
}

@OptIn(ExperimentalFoundationStyleApi::class)
private val cellStateKey = StyleStateKey(CellState.Normal)

@OptIn(ExperimentalFoundationStyleApi::class)
private fun StyleScope.cellSelected(value: Style) {
    state(
        key = cellStateKey,
        value = value,
        active = { key, state -> state[key] == CellState.Selected }
    )
}

@OptIn(ExperimentalFoundationStyleApi::class)
private fun StyleScope.cellInCheck(value: Style) {
    state(
        key = cellStateKey,
        value = value,
        active = { key, state -> state[key] == CellState.InCheck }
    )
}

@OptIn(ExperimentalFoundationStyleApi::class)
private fun StyleScope.cellAvailableMove(value: Style) {
    state(
        key = cellStateKey,
        value = value,
        active = { key, state -> state[key] == CellState.AvailableMove }
    )
}

@OptIn(ExperimentalFoundationStyleApi::class)
private var MutableStyleState.cellState: CellState
    get() = this[cellStateKey]
    set(value) { this[cellStateKey] = value }

@OptIn(ExperimentalFoundationStyleApi::class)
private val cellStyle = Style {
    background(LocalCellColor.currentValue)
    border(1.dp, colors.boardBorderColor)
    contentPadding(12.dp)

    cellSelected {
        background(colors.cellSelectedColor)
    }
    cellAvailableMove {
        background(colors.cellAvailableColor)
    }
    cellInCheck {
        background(colors.cellInCheckColor)
    }
}
