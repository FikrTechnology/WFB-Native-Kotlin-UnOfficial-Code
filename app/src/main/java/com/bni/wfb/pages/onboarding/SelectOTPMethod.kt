package com.bni.wfb.pages.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor


@Composable
fun SelectOtpMethod(btnSMS: () -> Unit, btnWA: () -> Unit, onClickBack: () -> Unit){
    Scaffold (
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
    ){paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Pilih metode OTP",
                    color = listColor.SmokyBlack,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700,
                )
                Text(
                    text = "Kami akan kirim 6 digit kode rahasia ke",
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

            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { btnSMS() }
                        .border(
                            border = BorderStroke(width = 1.dp, listColor.Gainsboro),
                            shape = RoundedCornerShape(8.dp)
                        ),
                ){
                    Row (
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            Image(
                                painter = painterResource(R.drawable.ico_sms_orange),
                                contentDescription = "SMS",
                                modifier = Modifier.height(24.dp)
                            )
                            Text(
                                "SMS",
                                color = listColor.SmokyBlack,
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Image(
                            painter = painterResource(R.drawable.ico_chevron_right_black),
                            contentDescription = "SMS Next",
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { btnWA() }
                        .border(
                            border = BorderStroke(width = 1.dp, listColor.Gainsboro),
                            shape = RoundedCornerShape(8.dp)
                        ),
                ){
                    Row (
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            Image(
                                painter = painterResource(R.drawable.ico_wa_orange),
                                contentDescription = "Whatsapp",
                                modifier = Modifier.height(24.dp)
                            )
                            Text(
                                "Whatsapp",
                                color = listColor.SmokyBlack,
                                fontWeight = FontWeight.W600,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        Image(
                            painter = painterResource(R.drawable.ico_chevron_right_black),
                            contentDescription = "Whatsapp Next",
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }
            }
        }
    }
}

