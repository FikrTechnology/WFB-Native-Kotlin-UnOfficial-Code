package com.bni.wfb.pages.onboarding

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.R
import com.bni.wfb.pages.component.CountryFlagsTextFieldPhoneNum
import com.bni.wfb.ui.theme.listColor
import com.rejowan.ccpc.CCPUtils
import com.rejowan.ccpc.Country
import com.rejowan.ccpc.CountryCodePicker
import com.rejowan.ccpc.CountryCodePickerTextField
import com.rejowan.ccpc.CountryPickerDialog
import com.rejowan.ccpc.PickerCustomization
import com.rejowan.ccpc.ViewCustomization
import com.togitech.ccp.component.TogiCountryCodePicker


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationEmailPhone(onClickNext: () -> Unit, onClickBack: () -> Unit) {
    var inpEmail by remember { mutableStateOf("") }
    var inpPhone by remember { mutableStateOf("")}
    var isFocusedEmail by remember { mutableStateOf(false) }
    var isFocusedPhone by remember { mutableStateOf(false) }
    val isButtonNextEnabled = inpEmail != "" && inpPhone != ""
    val maxLengthPhone = 15
    var country by remember {
        mutableStateOf(Country.Indonesia)
    }

    if (!LocalInspectionMode.current) {
        CCPUtils.getCountryAutomatically(context = LocalContext.current).let {
            it?.let {
                country = it
            }
        }
    }

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

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(100.dp),
                containerColor = Color.Transparent,
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                ) {
                    Button(
                        onClick = { onClickNext() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        enabled = isButtonNextEnabled,
                        colors = if (!isButtonNextEnabled) ButtonDefaults.buttonColors(
                            containerColor = listColor.FlashWhite
                        ) else ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue)
                    ) {
                        Text(
                            text = "Lanjut",
                            color = if (!isButtonNextEnabled) listColor.QuickSilver else listColor.SmokyBlack,
                            fontSize = 14.sp
                        )
                    }
                }
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
                    text = "Isi email dan nomor hp",
                    color = listColor.SmokyBlack,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700,
                )
                Text(
                    text = "Email akan digunakan sebagai username akun \n" +
                            "wondr business kamu. Pastiin email dan nomor HP kamu aktif.",
                    color = listColor.SmokyBlack,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W400
                )
            }

            Column (
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                TextField(
                    inpEmail, { inpEmail = it },
                    Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(
                                1.dp,
                                if (isFocusedEmail) SolidColor(listColor.keppel) else SolidColor(
                                    listColor.SilverSand
                                ) // Ubah warna border berdasarkan fokus
                            ), // Set border thickness to 1dp and color
                            RoundedCornerShape(8.dp) // Ensure border follows the same corner radius
                        )
                        .onFocusChanged { focusState ->
                            isFocusedEmail =
                                focusState.isFocused // Ubah status fokus ketika TextField fokus
                        },
                    label = {
                        Text(
                            "Email",
                            color = if (isFocusedEmail || inpEmail.isNotEmpty()) listColor.SmokyBlack else listColor.DarkLiver,
                            fontWeight = FontWeight.W400,
                            fontSize = if (isFocusedEmail || inpEmail.isNotEmpty()) 12.sp else 14.sp
                        )
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
                    trailingIcon = {
                        if (isFocusedEmail) {
                            IconButton(onClick = { inpEmail = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear, contentDescription = "Clear"
                                )
                            }
                        }
                    },
                )

                CountryFlagsTextFieldPhoneNum(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            BorderStroke(
                                1.dp,
                                if (isFocusedPhone) SolidColor(listColor.keppel) else SolidColor(
                                    listColor.SilverSand
                                ) // Ubah warna border berdasarkan fokus
                            ), // Set border thickness to 1dp and color
                            RoundedCornerShape(8.dp) // Ensure border follows the same corner radius
                        )
                        .onFocusChanged { focusState ->
                            isFocusedPhone =
                                focusState.isFocused // Ubah status fokus ketika TextField fokus
                        }
                    ,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = listColor.SmokyBlack,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp
                    ),
                    trailingIcon = {
                        if (isFocusedPhone) {
                            IconButton(onClick = { inpPhone = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Clear, contentDescription = "Clear"
                                )
                            }
                        }
                    },
                    label = {
                        Text(
                            text = "Phone Number",
                            color = if (isFocusedPhone || inpPhone.isNotEmpty()) listColor.SmokyBlack else listColor.DarkLiver,
                            fontWeight = FontWeight.W400,
                            fontSize = if (isFocusedPhone || inpPhone.isNotEmpty()) 12.sp else 14.sp
                        )
                    },
                    shape = RoundedCornerShape(10.dp),
                    onValueChange = { _, value, _ ->
//                        inpPhone = value
                        if (value.all(Char::isDigit) && value.length <= maxLengthPhone) {
                            inpPhone = value
                        }
                    },
                    showError = false,
                    number = inpPhone,
                    showSheet = true,
                    selectedCountry = country,
                    backgroundColor = Color.White,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VerificationEmailPhone(onClickNext = {}, onClickBack = {})
}