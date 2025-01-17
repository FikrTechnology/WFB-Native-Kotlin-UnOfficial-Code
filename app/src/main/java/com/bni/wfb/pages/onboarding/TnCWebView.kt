package com.bni.wfb.pages.onboarding

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bni.wfb.R
import com.bni.wfb.pages.component.tncShowFile
import com.bni.wfb.ui.theme.listColor
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@Composable
fun TncWebView(onClickNext: () -> Unit, onClickBack: () -> Unit) {
    val context = LocalContext.current
    var scrlstate = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    var isShowFloatingState by remember { mutableStateOf(true) }

    if(scrlstate.isScrollInProgress){
        if(scrlstate.value == scrlstate.maxValue){
            isShowFloatingState = false
        } else{
            isShowFloatingState = true
        }

    }

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

        floatingActionButton = {

            if (isShowFloatingState) {
                FloatingActionButton(
                    onClick = {

                        coroutineScope.launch {
                            scrlstate.animateScrollTo(scrlstate.maxValue)
                        }

                    },
                    containerColor = Color.Black,
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                        .size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ico_arrow_down_white),
                        contentDescription = "Scroll Down",
                        tint = Color.White
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        tncShowFile(onClickNext, paddingValues, scrlstate)
    }
}