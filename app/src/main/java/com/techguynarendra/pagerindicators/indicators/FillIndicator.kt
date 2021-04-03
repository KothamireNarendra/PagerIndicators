package com.techguynarendra.pagerindicators.indicators

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun FillPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
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

                val border = with(pagerState) {
                    if (isScrollInProgress && currentPageOffset != 0f) {    //border when scrolling
                        when (it) {
                            nextPage -> (maxBorderWidth - borderWidth) * scrollProgress + borderWidth           //border for next page
                            currentPage -> (maxBorderWidth - borderWidth) * (1 - scrollProgress) + borderWidth  //border for current page
                            else -> borderWidth
                        }
                    } else {   //border when not scrolling
                        when (it) {
                            currentPage -> maxBorderWidth
                            else -> borderWidth
                        }
                    }
                }

                Box(
                    Modifier
                        .size(width = indicatorSize, height = indicatorSize)
                        .border(
                            width = border,
                            color = activeColor,
                            shape = indicatorShape
                        )
                )
            }
        }

    }
}