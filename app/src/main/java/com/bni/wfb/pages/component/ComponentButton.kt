package com.bni.wfb.pages.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bni.wfb.ui.theme.listColor

@Composable
fun DoubleButton(
    modifier: Modifier,
    onclickBtnOne: () -> Unit = {},
    colorsBtnOne: ButtonColors? = null,
    borderBtnOne: BorderStroke? = null,
    textBtnOne: String,
    onclickBtnTwo: () -> Unit = {},
    colorsBtnTwo: ButtonColors? = null,
    borderBtnTwo: BorderStroke? = null,
    textBtnTwo: String,

    ) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        colorsBtnOne?.let {
            Button(
                onClick = { onclickBtnOne() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = it,
                border = borderBtnOne,
            ) {
                TextLabel1Bold(text = textBtnOne, color = listColor.SmokyBlack)
            }
        }

        colorsBtnTwo?.let {
            OutlinedButton(
                onClick = { onclickBtnTwo() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                border = borderBtnTwo,
                colors = it
            ) {
                TextLabel1Bold(text = textBtnTwo, color = listColor.SmokyBlack)
            }
        }
    }
}

@Composable
fun SingleButton(
    modifier: Modifier,
    onClickBtn: () -> Unit = {},
    enabled: Boolean = false,
    btnColors: ButtonColors = ButtonDefaults.buttonColors(),
    textBtn: String,
    textBtnColor: Color = Color.Unspecified
){
    Column(
        modifier = modifier,
    ) {
        Button(
            onClick = { onClickBtn() },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            enabled = enabled,
            colors = btnColors
        ) {
            TextLabel1Bold(
                text = textBtn,
                color = textBtnColor
            )
        }
    }
}