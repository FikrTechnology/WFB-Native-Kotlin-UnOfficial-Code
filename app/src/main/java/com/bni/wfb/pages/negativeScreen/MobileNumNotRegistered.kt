package com.bni.wfb.pages.negativeScreen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor
import com.google.accompanist.permissions.isGranted

@Composable
fun MobileNumNotRegistered() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(165.dp),
                containerColor = Color.Transparent
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue)
                    ) {
                        Text(
                            text = "Isi Nomor HP yang Terdaftar",
                            color = listColor.SmokyBlack,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600
                        )
                    }

                    OutlinedButton(
                        onClick = {
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        border = BorderStroke(1.dp, listColor.SonicSilver)
                    ) {
                        Text(
                            text = "Lihat Kantor Cabang Terdekat",
                            color = listColor.SmokyBlack,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            }
        },

        contentWindowInsets = WindowInsets(left = 0.dp, right = 0.dp, top = 11.dp, bottom = 0.dp),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.img_phone_num_not_registered),
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
                    text = "Nomor HP kamu belum terdaftar",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    color = listColor.SmokyBlack
                )

                Text(
                    text = "Untuk lanjut daftar wondr business, isi nomor HP yang udah terdaftar di bank (+62****3738) atau kunjungi cabang terdekat untuk perbarui data kamu. ",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400,
                    color = listColor.SmokyBlack,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewNegativeCase() {
    MobileNumNotRegistered()
}