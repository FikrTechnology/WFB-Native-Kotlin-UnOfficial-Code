package com.bni.wfb.pages.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor


@Composable
fun ShowTopBar(
    onlyLogo: Boolean = false,
    leading: Boolean = false,
    title: Boolean = false,
    trailing: Boolean = false,
    onClickLeading: () -> Unit = {},
    onClickTrailing: () -> Unit = {},
    imgLeading: Painter,
    textTitle: String? = null,
    imgTrailing: Painter? = null
) {
    Row(
        modifier = Modifier.padding(24.dp)
    ) {
        if (onlyLogo){
            Image(
                painter = imgLeading,
                contentDescription = null,
                modifier = Modifier.height(24.dp)
            )
        }

        if (leading){
            Image(
                painter = imgLeading,
                contentDescription = null,
                modifier = Modifier
                    .height(24.dp)
                    .clickable { onClickLeading() }
            )
        }

        if (title){
            textTitle?.let { TextBody2Reguler(text = it, color = listColor.SmokyBlack) }
        }

        if (trailing){
            imgTrailing?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                        .clickable { onClickTrailing() }
                )
            }
        }
    }
}
