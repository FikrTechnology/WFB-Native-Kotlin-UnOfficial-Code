package com.bni.wfb.pages.negativeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor


@Composable
fun MaxOTPScreen(){
    Scaffold (
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(100.dp),
                containerColor = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Button(
                        onClick = {  },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue)
                    ) {
                        Text(
                            text = "Kembali",
                            color = listColor.SmokyBlack,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        },

        contentWindowInsets = WindowInsets(left = 0.dp, right = 0.dp, top = 32.dp, bottom = 0.dp),
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(R.drawable.img_max_otp),
                contentDescription = "Phone number not Registered"
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Kamu belum bisa lanjut",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    color = listColor.SmokyBlack
                )

                Text(
                    text = "Kamu udah capai batas maksimum percobaan verifikasi OTP. Coba kembali dalam 15 menit ya!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = listColor.SmokyBlack,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


@Preview
@Composable
fun previewMaxOTPScreen(){
    MaxOTPScreen()
}