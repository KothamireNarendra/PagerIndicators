package com.techguynarendra.pagerindicators.indicators

import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
val PagerState.INVALID_PAGE: Int
    get() = -1

@ExperimentalPagerApi
val PagerState.nextPage: Int
    get() {
        if (isScrollInProgress && currentPageOffset != 0f) {
            val c = currentPage + currentPageOffset
            return if (c > currentPage) currentPage + 1 else currentPage - 1
        }
        return INVALID_PAGE
    }

@ExperimentalPagerApi
val PagerState.scrollProgress: Float
    get() {
        if (isScrollInProgress && currentPageOffset != 0f) {
            return if(nextPage < currentPage) {
                1 - currentPageOffset
            }else{
                currentPageOffset
            }
        }
        return 0f
    }

