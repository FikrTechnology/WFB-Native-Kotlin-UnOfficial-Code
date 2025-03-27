package com.bni.wfb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bni.wfb.pages.onboarding.InputAccount
import com.bni.wfb.pages.onboarding.LandingPage
import com.bni.wfb.pages.onboarding.OnboardingScreen
import com.bni.wfb.pages.onboarding.SelectOtpMethod
import com.bni.wfb.pages.onboarding.SubmissionType
import com.bni.wfb.pages.onboarding.TncWebView
import com.bni.wfb.pages.onboarding.VerificationCode
import com.bni.wfb.pages.onboarding.VerificationEmailPhone
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp(){
    val systemUiCont = rememberSystemUiController()
    systemUiCont.setSystemBarsColor(
        color = Color.White,
        darkIcons = true
    )
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "onboarding"){
        composable(route = "landingPage"){
            LandingPage(
                onClickDaftar = {},
                onClickMasuk = { navController.navigate("tncPage") }
            )
        }
        composable(route = "tncPage"){
            TncWebView(
                onClickBack = { navController.popBackStack() },
                onClickNext = { navController.navigate("onboarding") }
            )
        }
        composable(route = "onboarding"){
            OnboardingScreen(
                onClickHaveAcc = { navController.navigate("inpAccount") },
                onClickNotHaveAcc = {}
            )
//            OnboardingScreen2(
//                onClickHaveAcc = { navController.navigate("inpAccount") },
//                onClickNotHaveAcc = {}
//            )
        }
        composable(route = "inpAccount"){
            InputAccount(
                onClickNext = { navController.navigate("submissionType") },
                onClickBack = { navController.popBackStack() }
            )
        }
        composable(route = "submissionType"){
            SubmissionType(
                btnQris = { navController.navigate("verificationEmailPhone") },
                btnEdc = {},
                onClickBack = { navController.popBackStack() }
            )
        }
        composable(route = "verificationEmailPhone"){
            VerificationEmailPhone(
                onClickNext = { navController.navigate("verificationMethod") },
                onClickBack = { navController.popBackStack() }
            )
        }
        composable(route = "verificationMethod"){
            SelectOtpMethod(
                btnSMS = { navController.navigate("verificationCode") },
                btnWA = { navController.navigate("verificationCode") },
                onClickBack = { navController.popBackStack()}
            )
        }
        composable(route = "verificationCode"){
            VerificationCode(
                onClickBack = { navController.popBackStack() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApp()
}