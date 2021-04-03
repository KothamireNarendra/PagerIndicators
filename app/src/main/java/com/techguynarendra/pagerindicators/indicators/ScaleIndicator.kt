package com.techguynarendra.pagerindicators.indicators

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun ScalePagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    indicatorColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    indicatorWidth: Dp = 12.dp,
    @FloatRange(from = 1.1, to = 1.5) indicatorScale: Float = 1.5f,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = RoundedCornerShape(50),
) {
    val defaultScale = 1f

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            horizontalArrangement = Arrangement.spacedBy(spacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            repeat(pagerState.pageCount) {

                val scale = with(pagerState) {

                    if (isScrollInProgress && currentPageOffset != 0f) {    //scale when scrolling
                        when (it) {
                            nextPage -> (indicatorScale - defaultScale) * scrollProgress + defaultScale           //scale for next page
                            currentPage -> (indicatorScale - defaultScale) * (1 - scrollProgress) + defaultScale  //scale for current page
                            else -> defaultScale
                        }
                    } else {   //scale when not scrolling
                        when (it) {
                            currentPage -> indicatorScale
                            else -> defaultScale
                        }
                    }
                }

                val indicatorModifier = Modifier
                    .scale(scale)
                    .size(width = indicatorWidth, height = indicatorHeight)
                    .background(color = indicatorColor, shape = indicatorShape)

                Box(indicatorModifier)
            }
        }

    }
}