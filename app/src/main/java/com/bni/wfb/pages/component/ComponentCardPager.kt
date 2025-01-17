package com.bni.wfb.pages.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import com.bni.wfb.ui.theme.listColor

@Composable
fun ComponentCardPager(
    modifier: Modifier,
    vertical: Arrangement.Vertical,
    horizontal: Alignment.Horizontal,
    painter: Painter,
    textHeadline1: String,
    textbody: String
){
    Column(
        modifier = modifier,
        verticalArrangement = vertical,
        horizontalAlignment = horizontal
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TextHeadline1(text = textHeadline1)
            TextBody2Reguler(text = textbody, color = listColor.SmokyBlack)
        }

        Image(
            painter = painter,
            contentDescription = null
        )
    }
}