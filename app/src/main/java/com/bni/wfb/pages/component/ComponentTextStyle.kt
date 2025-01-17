package com.bni.wfb.pages.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bni.wfb.ui.theme.listColor

@Composable
fun textStyleGrey(): TextStyle {
    return TextStyle(
        color = listColor.SmokyBlack,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    )
}