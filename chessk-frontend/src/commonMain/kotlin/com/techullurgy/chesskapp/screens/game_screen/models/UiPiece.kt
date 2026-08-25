package com.techullurgy.chesskapp.screens.game_screen.models

import androidx.compose.runtime.Stable
import chesskapp.shared.generated.resources.Res
import chesskapp.shared.generated.resources.ic_black_bishop
import chesskapp.shared.generated.resources.ic_black_king
import chesskapp.shared.generated.resources.ic_black_knight
import chesskapp.shared.generated.resources.ic_black_queen
import chesskapp.shared.generated.resources.ic_black_rook
import chesskapp.shared.generated.resources.ic_black_sepoy
import chesskapp.shared.generated.resources.ic_white_bishop
import chesskapp.shared.generated.resources.ic_white_king
import chesskapp.shared.generated.resources.ic_white_knight
import chesskapp.shared.generated.resources.ic_white_queen
import chesskapp.shared.generated.resources.ic_white_rook
import chesskapp.shared.generated.resources.ic_white_sepoy
import org.jetbrains.compose.resources.DrawableResource

@Stable
sealed interface UiPiece {
    val color: UiPieceColor
    val icon: DrawableResource

    @Stable
    data class Sepoy(
        override val color: UiPieceColor
    ): UiPiece {
        override val icon: DrawableResource = when(color) {
            UiPieceColor.White -> Res.drawable.ic_white_sepoy
            UiPieceColor.Black -> Res.drawable.ic_black_sepoy
        }
    }

    @Stable
    data class Rook(
        override val color: UiPieceColor
    ): UiPiece {
        override val icon: DrawableResource = when(color) {
            UiPieceColor.White -> Res.drawable.ic_white_rook
            UiPieceColor.Black -> Res.drawable.ic_black_rook
        }
    }

    @Stable
    data class Knight(
        override val color: UiPieceColor
    ): UiPiece {
        override val icon: DrawableResource = when(color) {
            UiPieceColor.White -> Res.drawable.ic_white_knight
            UiPieceColor.Black -> Res.drawable.ic_black_knight
        }
    }

    @Stable
    data class Bishop(
        override val color: UiPieceColor
    ): UiPiece {
        override val icon: DrawableResource = when(color) {
            UiPieceColor.White -> Res.drawable.ic_white_bishop
            UiPieceColor.Black -> Res.drawable.ic_black_bishop
        }
    }

    @Stable
    data class Queen(
        override val color: UiPieceColor
    ): UiPiece {
        override val icon: DrawableResource = when(color) {
            UiPieceColor.White -> Res.drawable.ic_white_queen
            UiPieceColor.Black -> Res.drawable.ic_black_queen
        }
    }

    @Stable
    data class King(
        override val color: UiPieceColor
    ): UiPiece {
        override val icon: DrawableResource = when(color) {
            UiPieceColor.White -> Res.drawable.ic_white_king
            UiPieceColor.Black -> Res.drawable.ic_black_king
        }
    }
}