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
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun OpacityPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    activeColor: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
    opacity: Float = 0.3f,
    indicatorWidth: Dp = 12.dp,
    indicatorHeight: Dp = indicatorWidth,
    spacing: Dp = indicatorWidth,
    indicatorShape: Shape = RoundedCornerShape(50),
) {
    val maxOpacity = 1f

    require(opacity < maxOpacity) {
        "opacity should be less than 1f"
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

                val opaque = with(pagerState) {
                    if (isScrollInProgress && currentPageOffset != 0f) {    //opacity when scrolling
                        when (it) {
                            nextPage -> (maxOpacity - opacity) * scrollProgress + opacity           //opacity for next page
                            currentPage -> (maxOpacity - opacity) * (1 - scrollProgress) + opacity  //opacity for current page
                            else -> opacity
                        }
                    } else {   //opacity when not scrolling
                        when (it) {
                            currentPage -> maxOpacity
                            else -> opacity
                        }
                    }
                }

                val indicatorModifier = Modifier
                    .alpha(opaque)
                    .size(width = indicatorWidth, height = indicatorHeight)
                    .background(color = activeColor, shape = indicatorShape)

                Box(indicatorModifier)
            }
        }


    }
}