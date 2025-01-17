package com.bni.wfb.pages.component

import android.annotation.SuppressLint
import android.view.Gravity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.bni.wfb.ui.theme.listColor

@Composable
fun ShowDialogTwoButton(
    onDismissRequest: () -> Unit = {},
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    label: String,
    description: String,
    textBtnOne: String,
    textBtnTwo: String,
    btnActionOne: () -> Unit,
    btnActionTwo: () -> Unit
){
    // Jika showDialog bernilai true, maka tampilkan dialog
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside
            )
        ) {
            editContentGravity()

            // Draw a rectangle shape with rounded corners inside the dialog
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TextHeadline3(text = label)
                        TextBody2Reguler(text = description, color = listColor.SmokyBlack)
                    }

                    DoubleButton(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textBtnOne = textBtnOne,
                        onclickBtnOne = { btnActionOne() },
                        colorsBtnOne = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue),
                        textBtnTwo = textBtnTwo,
                        onclickBtnTwo = { btnActionTwo() },
                        borderBtnTwo = BorderStroke(1.dp, listColor.SonicSilver),
                        colorsBtnTwo = ButtonDefaults.buttonColors(containerColor = Color.White)
                    )
                }
            }
        }
    }
}

@SuppressLint("ComposableNaming")
@Composable
fun editContentGravity() {
    val dialogWindowProvider = LocalView.current.parent as? DialogWindowProvider

    dialogWindowProvider?.window?.setGravity(Gravity.BOTTOM)
}