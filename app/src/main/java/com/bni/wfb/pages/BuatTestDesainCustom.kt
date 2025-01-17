//package com.bni.wfb.pages.onboarding
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.WindowInsets
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.BottomAppBar
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.bni.wfb.R
//import com.bni.wfb.ui.theme.listColor
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.runtime.snapshotFlow
//import com.bni.wfb.pages.component.*
//import kotlinx.coroutines.flow.distinctUntilChanged
//
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun OnboardingScreen2(onClickHaveAcc: () -> Unit, onClickNotHaveAcc: () -> Unit) {
//    val coroutineScope = rememberCoroutineScope()
//
//    var animationProgress by remember { mutableStateOf(0f) }
//    val currentProgress = animationProgress % 1f
//    val currentPage = (animationProgress / 1f).toInt()
//
//    var isPaused by remember { mutableStateOf(false) }
//    var savedProgress by remember { mutableStateOf(0f) }
//    var showDialog by remember { mutableStateOf(false) }
//
//    val pages = listOf<@Composable () -> Unit>(
//        {
//            // Page 1 content
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 24.dp),
//                verticalArrangement = Arrangement.spacedBy(40.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    TextHeadline1(text = "Selamat datang di wondr business")
//                    TextBody2Reguler(text = "Kami siap menjadi teman setia untuk bisnis kamu.")
//                }
//                Image(
//                    painter = painterResource(id = R.drawable.img_onboard_1),
//                    contentDescription = null,
//                )
//            }
//        },
//        {
//            // Page 2 content
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 24.dp),
//                verticalArrangement = Arrangement.spacedBy(24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    TextHeadline1(text = "Terima pembayaran QRIS secara mudah")
//                    TextBody2Reguler(text = "Buat QRIS untuk ditempel di toko atau tentukan nominal khusus untuk langsung di-scan pelanggan.")
//                }
//                Image(
//                    painter = painterResource(id = R.drawable.img_onboard_2),
//                    contentDescription = null
//                )
//            }
//        },
//        {
//            // Page 3 content
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 24.dp),
//                verticalArrangement = Arrangement.spacedBy(24.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Column(
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    TextHeadline1(text = "Pantau performa bisnis kamu")
//                    TextBody2Reguler(text = "Dapatkan notifikasi real-time dan download laporan transaksi agar bisa terus pantau performa bisnismu.")
//                }
//                Image(
//                    painter = painterResource(id = R.drawable.img_onboard_3),
//                    contentDescription = null
//                )
//            }
//        }
//    )
//
//    LaunchedEffect(currentPage, isPaused) {
//        if (!isPaused) {
//            while (currentPage < pages.size) {
//                animationProgress += 0.01f
//                delay(30)
//                if (animationProgress % 1f == 0f) break
//            }
//        }
//    }
//
//    LaunchedEffect(showDialog) {
//        savedProgress = animationProgress
//        isPaused = showDialog
//    }
//
//    if (showDialog) {
//        showDialogTwoButton(
//            onDismissRequest = { showDialog = false },
//            label = "Sudah punya rekening BNI?",
//            description = "Kamu perlu punya rekening BNI untuk daftar ke wondr business.",
//            textBtnOne = "Sudah Punya",
//            textBtnTwo = "Buka Rekening via wondr by BNI",
//            btnActionOne = { onClickHaveAcc() },
//            btnActionTwo = { onClickNotHaveAcc() }
//        )
//    }
//
//    Scaffold(
//        containerColor = Color.White,
//        topBar = {
//            Row(
//                modifier = Modifier.padding(24.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.logo_wfb_orange),
//                    contentDescription = null,
//                    modifier = Modifier.height(24.dp)
//                )
//            }
//        },
//        contentWindowInsets = WindowInsets(left = 0.dp, right = 0.dp, top = 0.dp, bottom = 0.dp),
//        bottomBar = {
//            BottomAppBar(
//                modifier = Modifier.height(165.dp),
//                containerColor = Color.Transparent
//            ) {
//                Column(
//                    modifier = Modifier.padding(24.dp),
//                    verticalArrangement = Arrangement.spacedBy(8.dp)
//                ) {
//                    Button(
//                        onClick = { },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(48.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue)
//                    ) {
//                        TextLabel1Bold(text = "Daftar Jadi Merchant")
//                    }
//
//                    OutlinedButton(
//                        onClick = { showDialog = true },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(48.dp),
//                        border = BorderStroke(1.dp, listColor.SonicSilver)
//                    ) {
//                        TextLabel1Bold(text = "Masuk")
//                    }
//                }
//            }
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .pointerInput(Unit) {
//                    detectTapGestures(
//                        onTap = { offset ->
//                            val screenWidth = size.width
//                            val tapAreaWidth = screenWidth * 0.15
//                            if (offset.x < tapAreaWidth && currentPage > 0) {
//                                coroutineScope.launch { animationProgress -= 1f }
//                            } else if (offset.x > screenWidth - tapAreaWidth && currentPage < pages.size - 1) {
//                                coroutineScope.launch { animationProgress += 1f }
//                            }
//                        },
//                        onLongPress = {
//                            savedProgress = animationProgress
//                            isPaused = true
//                        },
//                        onPress = {
//                            tryAwaitRelease()
//                            isPaused = false
//                        },
//                    )
//                },
//        ) {
//            ProgressBarIndicator(
//                currentPage = currentPage,
//                progress = currentProgress,
//                pageCount = pages.size
//            )
//
//            DynamicPager(
//                pages = pages,
//                onPageChanged = { page ->
//                    animationProgress = page.toFloat()
//                }
//            )
//        }
//    }
//}
//
//
//
