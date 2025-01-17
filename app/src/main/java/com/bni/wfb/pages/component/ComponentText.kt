package com.bni.wfb.pages.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.bni.wfb.ui.theme.listColor

//////////////////////////////// TEXT Headline Styles //////////////////////////////////
@Composable
fun TextHeadline1(
    text: String
){
    Text(
        text = text,
        color = listColor.SmokyBlack,
        fontSize = 28.sp,
        fontWeight = FontWeight.W800,
    )
}

@Composable
fun TextHeadline2(
    text: String
){
    Text(
        text = text,
        color = listColor.SmokyBlack,
        fontSize = 24.sp,
        fontWeight = FontWeight.W700,
    )
}

@Composable
fun TextHeadline3(
    text: String
){
    Text(
        text = text,
        color = listColor.SmokyBlack,
        fontSize = 18.sp,
        fontWeight = FontWeight.W700,
    )
}



//////////////////////////////// TEXT Body Style //////////////////////////////////

@Composable
fun TextBody2Reguler(
    text: String,
    color: Color = Color.Unspecified
){
    Text(
        text = text,
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
    )
}




////////////////////////////////// TEXT Label Style //////////////////////////////////
@Composable
fun TextLabel1Bold(
    text: String,
    color: Color = Color.Unspecified
){
    Text(
        text = text,
        color = color,
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
    )
}

@Composable
fun TextLabel2Reguler(
    text: String,
    color: Color = Color.Unspecified
){
    Text(
        text = text,
        color = color,
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
    )
}


////////////////////////////////// TEXT Underline Style //////////////////////////////////
@Composable
fun TextUnderline2Bold(
    text: String,
    color: Color = Color.Unspecified
){
    Text(
        text = text,
        color = color,
        textDecoration = TextDecoration.Underline,
        fontSize = 14.sp,
        fontWeight = FontWeight.W700
    )
}