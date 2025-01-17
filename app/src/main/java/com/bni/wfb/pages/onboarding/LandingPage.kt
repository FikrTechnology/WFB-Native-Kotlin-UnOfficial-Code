package com.bni.wfb.pages.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor

@Composable
fun LandingPage(onClickMasuk: () -> Unit, onClickDaftar: () -> Unit){
    // Tampilan Landing Page
    Scaffold(
        containerColor = Color.White,

        topBar = {
            Row(
                modifier = Modifier.padding(24.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_wfb_orange),
                    contentDescription = null,
                    modifier = Modifier.height(24.dp)
                )
            }
        },

        contentWindowInsets = WindowInsets(left = 0.dp, right = 0.dp, top = 0.dp, bottom = 0.dp),

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
                        onClick = { onClickDaftar() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue)
                    ) {
                        Text(
                            text = "Daftar Jadi Merchant",
                            color = listColor.SmokyBlack,
                            fontSize = 14.sp
                        )
                    }

                    OutlinedButton(
                        onClick = { onClickMasuk() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        border = BorderStroke(1.dp, listColor.SonicSilver)
                    ) {
                        Text(
                            text = "Masuk",
                            color = listColor.SmokyBlack,
                            fontSize = 14.sp
                        )
                    }


                }
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(60.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(45.dp)
                ) {
                    Text(
                        text = "Tingatkan transaksi usahamu dengan wondr business!",
                        color = listColor.SmokyBlack,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.W800,
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.img_landing_page),
                    contentDescription = null,
                )
            }
        }
    }
}