package com.bni.wfb.pages.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.ui.theme.listColor
import com.rejowan.ccpc.CCPTransformer
import com.rejowan.ccpc.CCPValidator
import com.rejowan.ccpc.Country
import com.rejowan.ccpc.CountryCodePicker
import com.rejowan.ccpc.PickerCustomization
import com.rejowan.ccpc.ViewCustomization


@Composable
fun CountryFlagsTextFieldPhoneNum(
    modifier: Modifier = Modifier,
    number: String,
    onValueChange: (countryCode: String, value: String, isValid: Boolean) -> Unit,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    showError: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    selectedCountry: Country = Country.Bangladesh,
    countryList: List<Country> = Country.getAllCountries(),
    viewCustomization: ViewCustomization = ViewCustomization(),
    pickerCustomization: PickerCustomization = PickerCustomization(),
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    showSheet: Boolean = false,
    itemPadding: Int = 10,
) {

    val context = LocalContext.current

    var country by remember {
        mutableStateOf(selectedCountry)
    }


    val validatePhoneNumber = remember(context) {
        CCPValidator(context = context)
    }

    var isNumberValid: Boolean by rememberSaveable(country, number) {
        mutableStateOf(
            validatePhoneNumber(
                number = number, countryCode = country.countryCode
            ),
        )
    }


    TextField(
        value = number,
        onValueChange = {
            isNumberValid = validatePhoneNumber(
                number = it, countryCode = country.countryCode
            )
            onValueChange(country.countryCode, it, isNumberValid)
        },
        modifier = modifier,
        textStyle = textStyle,
        singleLine = true,
        shape = shape,
        label = label,
        placeholder = placeholder,
        leadingIcon = {
            CountryCodePicker(
                selectedCountry = country,
                countryList = countryList,
                onCountrySelected = {
                    country = it
                    isNumberValid = validatePhoneNumber(
                        number = number, countryCode = it.countryCode
                    )
                    onValueChange(it.countryCode, number, isNumberValid)
                },
                viewCustomization = viewCustomization,
                pickerCustomization = pickerCustomization,
                backgroundColor = backgroundColor,
                textStyle = TextStyle(
                    color = listColor.keppel,
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp
                ),
                showSheet = showSheet,
                itemPadding = itemPadding
            )

        },
        trailingIcon = trailingIcon,
        isError = !isNumberValid && number.isNotEmpty() && showError,
        visualTransformation = CCPTransformer(context, country.countryIso),
        enabled = enabled,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = colors
    )


}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    var value by remember {
        mutableStateOf("")
    }

    CountryFlagsTextFieldPhoneNum(onValueChange = { _, number, _ ->
        value = number

    }, number = value)
}