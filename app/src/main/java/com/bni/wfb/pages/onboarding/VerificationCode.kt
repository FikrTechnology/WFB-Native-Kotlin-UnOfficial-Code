package com.bni.wfb.pages.onboarding

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.pages.component.CountdownTimer
import com.bni.wfb.ui.theme.listColor


@Composable
fun VerificationCode(onClickBack: () -> Unit) {
    var otpText by remember {
        mutableStateOf("")
    }
    var isTimerCountdownFinsihed by remember { mutableStateOf(false) }
    var otpNotValid by remember { mutableStateOf(false) }
    Scaffold(
        containerColor = Color.White,
        topBar = {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ico_arrow_left_black),
                    contentDescription = null,
                    modifier = Modifier
                        .height(24.dp)
                        .clickable { onClickBack() }
                )
            }
        },
        contentWindowInsets = WindowInsets(left = 24.dp, right = 24.dp, top = 0.dp, bottom = 0.dp),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Cek SMS kamu ya!",
                    color = listColor.SmokyBlack,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700,
                )
                Text(
                    text = "Masukin kode OTP 6 digit yang dikirim ke nomor",
                    color = listColor.SmokyBlack,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
                Text(
                    text = "+628*****678.",
                    color = listColor.SmokyBlack,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(64.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    BasicTextField(
                        value = otpText,
                        onValueChange = { newText ->
                            if (newText.length <= 6) {  // Mengizinkan hingga 6 digit
                                otpText = newText
                            }
                            if (otpText.length == 6 && otpText == "123456") {
                                otpNotValid = true
                            } else {
                                otpNotValid = false
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            repeat(6) { index ->
                                val number = when {
                                    index >= otpText.length -> ""
                                    else -> otpText[index]
                                }

                                Column(
                                    verticalArrangement = Arrangement.spacedBy(6.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = number.toString(),
                                        color = if (otpNotValid) listColor.IndianRed else listColor.SmokyBlack,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.W700,
                                    )

                                    Box(
                                        modifier = Modifier
                                            .width(40.dp)
                                            .height(2.dp)
                                            .background(if (otpNotValid) listColor.IndianRed else if (number.toString() == "") listColor.QuickSilver else listColor.keppel)
                                    )
                                }
                            }
                        }
                    }

                    if (otpNotValid) {
                        Text(
                            text = "Masukin kode yang valid (1 dari 3)",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W400,
                            color = listColor.IndianRed
                        )
                    } else {
                        Text(
                            text = "",
                            fontSize = 14.sp
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!isTimerCountdownFinsihed) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Kirim ulang kode dalam",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400
                            )
                            CountdownTimer(
                                timeInSeconds = 90,
                                onTimeFinished = { isTimerCountdownFinsihed = true })
                        }
                    } else {
                        Text(
                            text = "Kirim ulang kode (1 dari 3)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.W400,
                            color = listColor.SmokyBlack
                        )

                        Row(
                        ) {
                            Text(
                                text = "SMS",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W700,
                                color = listColor.MangoTango
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "atau",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                                color = listColor.SmokyBlack
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Whatsapp",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W700,
                                color = listColor.MangoTango
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewOTPMethod() {
    VerificationCode(onClickBack = {})
}