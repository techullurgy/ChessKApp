package com.techullurgy.chesskapp.screens.game_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.withSaveLayer
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.MultiContentMeasurePolicy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.chesskapp.core.utils.DeviceConfigurationType
import com.techullurgy.chesskapp.screens.game_screen.adaptive.LocalDeviceType
import com.techullurgy.chesskapp.screens.game_screen.adaptive.ProvideDeviceType
import com.techullurgy.chesskapp.screens.game_screen.models.GameState
import com.techullurgy.chesskapp.screens.game_screen.models.Initial
import com.techullurgy.chesskapp.screens.game_screen.models.UiBoard
import com.techullurgy.chesskapp.screens.game_screen.models.UiParticipant
import com.techullurgy.chesskapp.screens.game_screen.models.UiPieceColor
import com.techullurgy.chesskapp.screens.game_screen.models.UiTurn
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

@Composable
internal fun InfoBar(
    state: GameState.Data,
    modifier: Modifier = Modifier
) {
    val deviceType = LocalDeviceType.current

    Layout(
        contents = listOf(
            {
                Photo(state.you.dpUrl, state.you.assignedColor)
                Name(state.you.name)
                Timer(state.yourTime)
            },
            {
                Photo(state.other.dpUrl, state.other.assignedColor)
                Name(state.other.name)
                Timer(state.opponentTime)
            }
        ),
        measurePolicy = InfoBarLayoutMeasurePolicy(deviceType),
        modifier = modifier
    )
}

private class InfoBarLayoutMeasurePolicy(
    private val deviceType: DeviceConfigurationType
): MultiContentMeasurePolicy {
    override fun MeasureScope.measure(
        measurables: List<List<Measurable>>,
        constraints: Constraints
    ): MeasureResult {
        val player1Photo = measurables[0][0]
        val player1Name = measurables[0][1]
        val player1Time = measurables[0][2]

        val player2Photo = measurables[1][0]
        val player2Name = measurables[1][1]
        val player2Time = measurables[1][2]

        val photoMaxSize = 250.dp.roundToPx()

        return when(deviceType) {
            DeviceConfigurationType.PhonePortrait,
            DeviceConfigurationType.TabletPortrait -> rowTypeMeasurementPlacement(
                player1Photo = player1Photo,
                player1Name = player1Name,
                player1Time = player1Time,
                player2Photo = player2Photo,
                player2Name = player2Name,
                player2Time = player2Time,
                constraints = constraints,
                photoMaxSize = photoMaxSize
            )
            DeviceConfigurationType.PhoneLandscape,
            DeviceConfigurationType.TabletLandscape,
            DeviceConfigurationType.Desktop -> stackTypeMeasurementPlacement(
                player1Photo = player1Photo,
                player1Name = player1Name,
                player1Time = player1Time,
                player2Photo = player2Photo,
                player2Name = player2Name,
                player2Time = player2Time,
                constraints = constraints,
                photoMaxSize = photoMaxSize
            )
        }
    }

    private fun MeasureScope.rowTypeMeasurementPlacement(
        player1Photo: Measurable,
        player1Name: Measurable,
        player1Time: Measurable,
        player2Photo: Measurable,
        player2Name: Measurable,
        player2Time: Measurable,
        constraints: Constraints,
        photoMaxSize: Int,
    ): MeasureResult {
        val totalWidth = constraints.maxWidth
        val totalHeight = constraints.maxHeight

        val player1NameP = player1Name.measure(constraints.copy(minWidth = 0, maxWidth = totalWidth/2, minHeight = 0))
        val player1TimeP = player1Time.measure(constraints.copy(minWidth = 0, maxWidth = totalWidth/2, minHeight = 0))
        val player2NameP = player2Name.measure(constraints.copy(minWidth = 0, maxWidth = totalWidth/2, minHeight = 0))
        val player2TimeP = player2Time.measure(constraints.copy(minWidth = 0, maxWidth = totalWidth/2, minHeight = 0))

        val occupiedMaxHeight = maxOf(
            player1NameP.height + player1TimeP.height,
            player2NameP.height + player2TimeP.height
        )

        val photoAspectWidth = (totalWidth / 2) / 3
        val remainingHeight = totalHeight - occupiedMaxHeight

        val photoSize = minOf(photoAspectWidth, remainingHeight).coerceAtMost(photoMaxSize)

        val photoConstraints = Constraints.fixed(photoSize, photoSize)

        val player1PhotoP = player1Photo.measure(photoConstraints)
        val player2PhotoP = player2Photo.measure(photoConstraints)

        return layout(totalWidth, totalHeight) {
            val player1TotalHeight = player1NameP.height + player1TimeP.height + player1PhotoP.height

            val player2TotalHeight = player2NameP.height + player2TimeP.height + player2PhotoP.height

            val player1CenterX = totalWidth / 4
            var player1StartY = (totalHeight - player1TotalHeight) / 2 // Middle

            player1PhotoP.place(
                player1CenterX - player1PhotoP.width / 2,
                player1StartY
            )
            player1StartY += player1PhotoP.height

            player1NameP.place(
                player1CenterX - player1NameP.width / 2,
                player1StartY
            )
            player1StartY += player1NameP.height

            player1TimeP.place(
                player1CenterX - player1TimeP.width / 2,
                player1StartY
            )

            val player2CenterX = totalWidth * 3 / 4
            var player2StartY = (totalHeight - player2TotalHeight) / 2 // Middle

            player2PhotoP.place(
                player2CenterX - player2PhotoP.width / 2,
                player2StartY
            )
            player2StartY += player2PhotoP.height

            player2NameP.place(
                player2CenterX - player2NameP.width / 2,
                player2StartY
            )
            player2StartY += player2NameP.height

            player2TimeP.place(
                player2CenterX - player2TimeP.width / 2,
                player2StartY
            )
        }
    }

    private fun MeasureScope.stackTypeMeasurementPlacement(
        player1Photo: Measurable,
        player1Name: Measurable,
        player1Time: Measurable,
        player2Photo: Measurable,
        player2Name: Measurable,
        player2Time: Measurable,
        constraints: Constraints,
        photoMaxSize: Int,
    ): MeasureResult {
        return layout(0, 0) {}
    }
}

@Composable
private fun Photo(
    profileUrl: String?,
    color: UiPieceColor
) {
    Box(
        modifier = Modifier
            .drawWithContent {
                drawIntoCanvas {
                    it.withSaveLayer(
                        bounds = size.toRect(),
                        paint = Paint()
                    ) {
                        drawContent()

                        val colorSize = maxOf(
                            30.dp.toPx(),
                            size.minDimension * 0.3f
                        ).let { dim ->
                            Size(dim, dim)
                        }

                        translate(
                            left = size.width - colorSize.width,
                            top = size.height - colorSize.height
                        ) {
                            drawCircle(
                                color = Color.White,
                                center = colorSize.center,
                                radius = colorSize.minDimension * .5f,
                                blendMode = BlendMode.Clear
                            )
                            drawCircle(
                                color = when (color) {
                                    UiPieceColor.White -> Color.White
                                    UiPieceColor.Black -> Color.Black
                                },
                                center = colorSize.center,
                                radius = colorSize.minDimension * .35f
                            )
                        }
                    }
                }
            }
            .padding(4.dp)
            .dropShadow(CircleShape) {
                this.alpha = 0.3f
                radius = 10f
                spread = 6f
                blendMode = BlendMode.Overlay
            }
            .background(Color.Blue, CircleShape),
    ) {
        // Image of Player
    }
}

@Composable
private fun Name(name: String) {
    Text(
        text = name,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        softWrap = false,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        autoSize = TextAutoSize.StepBased(minFontSize = 30.sp, maxFontSize = 40.sp)
    )
}

@Composable
private fun Timer(
    time: Long,
    modifier: Modifier = Modifier
) {
    val formattedTime = time.milliseconds.toComponents { minutes, seconds, _ ->
        val mm = minutes.toString().padStart(2, '0')
        val ss = seconds.toString().padStart(2, '0')
        "$mm:$ss"
    }

    Box(modifier, Alignment.Center) {
        Text(
            text = formattedTime,
            fontWeight = FontWeight.Bold,
            softWrap = false,
            maxLines = 1,
            autoSize = TextAutoSize.StepBased(minFontSize = 24.sp, maxFontSize = 30.sp)
        )
    }
}

private class DeviceTypePreviewParameterProvider: PreviewParameterProvider<DeviceConfigurationType> {
    override fun getDisplayName(index: Int): String {
        return DeviceConfigurationType.entries.toList()[index].name
    }

    override val values: Sequence<DeviceConfigurationType>
        get() = DeviceConfigurationType.entries.asSequence()
}