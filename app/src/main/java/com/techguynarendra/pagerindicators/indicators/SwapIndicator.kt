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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun SwapPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    inactiveColor: Color = activeColor.copy(ContentAlpha.disabled),
    indicatorWidth: Dp = 12.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = RoundedCornerShape(50),
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            repeat(pagerState.pageCount) {

                val opacity = with(pagerState) {
                    if (isScrollInProgress && currentPageOffset != 0f) {
                        return@with if (currentPage == it || nextPage == it) 0f else 1f
                    }
                    1f
                }

                Box(
                    Modifier
                        .alpha(opacity)
                        .size(width = indicatorWidth, height = indicatorHeight)
                        .background(color = inactiveColor, shape = indicatorShape)
                )
            }
        }

        Box(
            Modifier
                .offset {
                    val scrollPosition = pagerState.currentPage + pagerState.currentPageOffset
                    IntOffset(
                        x = ((spacing + indicatorWidth) * scrollPosition).roundToPx(),
                        y = 0
                    )
                }
                .size(width = indicatorHeight, height = indicatorHeight)
                .background(
                    color = activeColor,
                    shape = indicatorShape,
                )
        )

        if (pagerState.isScrollInProgress && pagerState.currentPageOffset != 0f) {
            Box(
                Modifier
                    .offset {
                        val scrollPosition =
                            pagerState.currentPage + (1 - pagerState.currentPageOffset)
                        IntOffset(
                            x = ((spacing + indicatorWidth) * scrollPosition).roundToPx(),
                            y = 0
                        )
                    }
                    .size(width = indicatorHeight, height = indicatorHeight)
                    .background(
                        color = inactiveColor,
                        shape = indicatorShape,
                    )
            )
        }
    }
}