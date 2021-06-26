package com.techguynarendra.pagerindicators.indicators

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.lerp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun WormPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorWidth: Dp = 12.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = RoundedCornerShape(6.dp),
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val indicatorModifier = Modifier
                .size(width = indicatorWidth, height = indicatorHeight)
                .background(
                    color = inactiveColor,
                    shape = indicatorShape)

            repeat(pagerState.pageCount) {
                Box(indicatorModifier)
            }
        }

        val w: Dp
        val xOffset: Dp
        if (pagerState.currentPageOffset <= 0.5) {
            val f = pagerState.currentPageOffset / 0.5f
            w = lerp(indicatorWidth, indicatorWidth * 3, f)
            xOffset = (spacing + indicatorWidth) * pagerState.currentPage
        } else {
            val f = ((pagerState.currentPageOffset - 0.5f) / 0.5f)
            w = lerp(indicatorWidth * 3, indicatorWidth, f)
            val scrollPosition = pagerState.currentPage + f
            xOffset = (spacing + indicatorWidth) * scrollPosition
        }

        Box(
            Modifier
                .offset {
                    IntOffset(
                        x = xOffset.roundToPx(),
                        y = 0
                    )
                }
                .size(width = w, height = indicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape,
                )
        )
    }
}