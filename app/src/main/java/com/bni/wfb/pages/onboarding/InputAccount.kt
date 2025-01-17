package com.bni.wfb.pages.onboarding

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.pages.component.ShowTopBar
import com.bni.wfb.pages.component.*
import com.bni.wfb.ui.theme.listColor

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun InputAccount(onClickNext: () -> Unit, onClickBack: () -> Unit) {
    var inpAccount by remember { mutableStateOf("") }
    var accountName by remember { mutableStateOf("-") }
    var isFocused by remember { mutableStateOf(false) }
    val isButtonEnabled = inpAccount.isNotEmpty()
    val isButtonNextEnabled = accountName != "" && accountName != "-"
    val maxAccNum = 12
    var isAccNotFound by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            ShowTopBar(
                leading = true,
                imgLeading = painterResource(id = R.drawable.ico_arrow_left_black),
                onClickLeading = { onClickBack() }
            )
        },

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(100.dp),
                containerColor = Color.Transparent,
            ) {
                SingleButton(
                    modifier = Modifier.padding(24.dp),
                    onClickBtn = { onClickNext() },
                    enabled = isButtonNextEnabled,
                    btnColors = if (!isButtonNextEnabled) ButtonDefaults.buttonColors(containerColor = listColor.FlashWhite) else ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue),
                    textBtn = "Lanjut",
                    textBtnColor = if (!isButtonNextEnabled) listColor.QuickSilver else listColor.SmokyBlack
                )
            }
        },

        contentWindowInsets = WindowInsets(left = 0.dp, right = 0.dp, top = 0.dp, bottom = 0.dp),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextHeadline2(text = "Isi data rekening")
                    TextBody2Reguler(text = "Pastiin nomor rekening sudah sesuai dan masih aktif, lalu pilih Periksa sebelum lanjut.", color = listColor.SmokyBlack)
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Column (
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        TextField(
                            value = inpAccount,
                            onValueChange = {
                                isAccNotFound = false
                                if (it.all(Char::isDigit) && it.length <= maxAccNum) {
                                    inpAccount = it
                                }
                            },
                            label = {
                                if (isFocused || isButtonEnabled) TextLabel2Reguler(text = "Nomor Rekening", color = listColor.SmokyBlack)
                                else TextBody2Reguler(text = "Nomor Rekening", color = listColor.DarkLiver)
                            },
                            textStyle = TextStyle(
                                color = listColor.SmokyBlack,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = Color.Black
                            ),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    BorderStroke(
                                        1.dp,
                                        if (isAccNotFound) SolidColor(listColor.IndianRed) else if (isFocused) SolidColor(
                                            listColor.keppel
                                        ) else SolidColor(
                                            listColor.SilverSand
                                        ) // Ubah warna border berdasarkan fokus
                                    ), // Set border thickness to 1dp and color
                                    RoundedCornerShape(8.dp) // Ensure border follows the same corner radius
                                )
                                .onFocusChanged { focusState ->
                                    isFocused =
                                        focusState.isFocused // Ubah status fokus ketika TextField fokus
                                },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            trailingIcon = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .clickable(enabled = isButtonEnabled) {
                                            if (inpAccount.length < 10) {
                                                isAccNotFound = true
                                                accountName = "-"
                                            } else {
                                                isAccNotFound = false
                                                accountName = "P***a N****a"
                                            }
                                        }
                                        .padding(end = 12.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.ico_search_orange),
                                        contentDescription = "Search",
                                        tint = if (isButtonEnabled) listColor.MangoTango else listColor.QuickSilver
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    TextUnderline2Bold(
                                        text = "Periksa",
                                        color = if (isButtonEnabled) listColor.MangoTango else listColor.QuickSilver
                                    )
                                }
                            }
                        )

                        if (isAccNotFound){
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    painter = painterResource(R.drawable.ico_error_red),
                                    contentDescription = "Info",
                                    tint = listColor.IndianRed,
                                    modifier = Modifier.width(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                TextLabel2Reguler(
                                    text = "Nama pemilik rekening",
                                    color = listColor.IndianRed
                                )
                            }
                        }
                    }

                    TextField(
                        value = accountName,
                        onValueChange = {  },
                        label = {
                            TextLabel2Reguler(
                                text = "Nama pemilik rekening",
                                color = listColor.SmokyBlack
                            )
                        },
                        textStyle = textStyleGrey(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    1.dp,
                                    SolidColor(listColor.FlashWhite)
                                ), // Set border thickness to 1dp and color
                                RoundedCornerShape(8.dp) // Ensure border follows the same corner radius
                            ),
                        readOnly = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = listColor.FlashWhite,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.Black
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }
        }
    }
}