package com.bni.wfb.pages.component

import android.view.ViewGroup
import android.webkit.WebView
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.bni.wfb.ui.theme.listColor

@Composable
fun tncShowFile(onClickNext: () -> Unit, paddingValues: PaddingValues, scrlState: ScrollState){
    var isChecked by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
        .verticalScroll(scrlState)){
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = { it.loadUrl("file:///android_asset/syaratdanketentuanMerchant.html") },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { isChecked = it },
                    colors = CheckboxDefaults.colors(Color.Black)
                )

                Text(
                    "Saya sepenuhnya paham dan setuju dengan Syarat & Ketentuan di atas. "
                )
            }

            Button(
                onClick = { onClickNext() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                enabled = isChecked,
                colors = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue)
            ) {
                Text(
                    text = "Lanjut",
                    color = listColor.SmokyBlack,
                    fontSize = 14.sp
                )
            }
        }
    }

}