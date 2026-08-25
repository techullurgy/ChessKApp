package com.techullurgy.chesskapp.core.utils

import androidx.compose.ui.layout.LayoutModifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.unit.Constraints

class SquareModifier: LayoutModifier {
    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val placeable = measurable.measure(constraints.toSquareConstraints())
        return layout(placeable.width, placeable.height) { placeable.place(0, 0) }
    }
}

private fun Constraints.toSquareConstraints(): Constraints {
    return if(maxWidth > maxHeight) {
        copy(maxWidth = maxHeight)
    } else if (maxWidth < maxHeight) {
        copy(maxHeight = maxWidth)
    } else this
}