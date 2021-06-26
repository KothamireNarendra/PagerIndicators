package com.techguynarendra.pagerindicators.indicators

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun JumpPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    borderWidth: Dp = 2.dp,
    indicatorSize: Dp = 12.dp,
    spacing: Dp = indicatorSize,
    indicatorShape: Shape = RoundedCornerShape(50),
) {
    val maxBorderWidth = indicatorSize / 2

    require(borderWidth <= maxBorderWidth) {
        "borderWidth should be less than or equal to half of the indicatorSize"
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(pagerState.pageCount) {

                val indicatorModifier = Modifier
                    .size(width = indicatorSize, height = indicatorSize)
                    .background(color = inactiveColor, shape = indicatorShape)

                    Box(indicatorModifier)

            }
        }

        Box(
            Modifier
                .offset {
                    val x1 = ((spacing + indicatorSize) * pagerState.currentPage)
                    val midX = (spacing + indicatorSize)/2
                    val angelInRadian = Math.toRadians(180.0)
                    val pointX = x1.toPx() + indicatorSize.toPx() + (midX.toPx() * -Math.cos(angelInRadian * pagerState.scrollProgress))
                    val pointY = midX.toPx() * -Math.sin(angelInRadian  * pagerState.scrollProgress)

                    IntOffset(
                        x = pointX.toInt(),
                        y = pointY.toInt()
                    )
                }
                .size(width = indicatorSize, height = indicatorSize)
                .border(
                    width = indicatorSize,
                    color = activeColor,
                    shape = indicatorShape
                )
        )
    }
}