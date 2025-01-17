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
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor


@Composable
fun SubmissionType(btnQris: () -> Unit, btnEdc: () -> Unit, onClickBack: () -> Unit){
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
                    text = "Jenis pengajuan",
                    color = listColor.SmokyBlack,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700,
                )
                Text(
                    text = "Pilih metode pembayaran yang mau kamu pakai untuk transaksi bisnis kamu.",
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
                        .clickable { btnQris() }
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
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(0.9f),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painter = painterResource(R.drawable.ico_qris_orange),
                                    contentDescription = "QRIS",
                                    modifier = Modifier.height(24.dp)
                                )
                                Text(
                                    "QRIS",
                                    color = listColor.SmokyBlack,
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }

                            Text(
                                "Transaksi pakai scan QR Code ke hp kamu dari berbagai aplikasi pembayaran.",
                                color = listColor.SmokyBlack,
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp
                            )
                        }

                        Image(
                            painter = painterResource(R.drawable.ico_chevron_right_black),
                            contentDescription = "QRIS Next",
                            modifier = Modifier.height(24.dp)
                        )
                    }
                }

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
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
                        Column (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painter = painterResource(R.drawable.ico_edc_gray),
                                    contentDescription = "EDC",
                                    modifier = Modifier.height(24.dp)
                                )

                                Text(
                                    "EDC",
                                    color = listColor.SonicSilver,
                                    fontWeight = FontWeight.W600,
                                    fontSize = 14.sp,
                                    modifier = Modifier.padding(start = 8.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = "Segera Hadir",
                                    color = listColor.SmokyBlack,
                                    modifier = Modifier
                                        .background(
                                            color = listColor.MaximumGreenYellow,
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(5.dp),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W600,
                                )
                            }

                            Text(
                                "Transaksi mudah pakai kartu debit di mesin EDC. Untuk sementara, buatnya di cabang terdekat ya.",
                                color = listColor.QuickSilver,
                                fontWeight = FontWeight.W400,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}