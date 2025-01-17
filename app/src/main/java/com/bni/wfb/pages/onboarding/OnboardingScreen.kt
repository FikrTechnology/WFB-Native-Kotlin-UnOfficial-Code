package com.bni.wfb.pages.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bni.wfb.R
import com.bni.wfb.ui.theme.listColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.snapshotFlow
import com.bni.wfb.pages.component.*
import kotlinx.coroutines.flow.distinctUntilChanged


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onClickHaveAcc: () -> Unit, onClickNotHaveAcc: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()

    var animationProgress by remember { mutableStateOf(0f) }
    val currentProgress = animationProgress % 1f
    val currentPage = (animationProgress / 1f).toInt()

    var isPaused by remember { mutableStateOf(false) }
    var savedProgress by remember { mutableStateOf(0f) }

    var showDialog by remember { mutableStateOf(false) }

    // Menangani perubahan halaman manual
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage != currentPage) {
            animationProgress = pagerState.currentPage.toFloat()
        }
    }

    LaunchedEffect(key1 = pagerState.currentPage, key2 = isPaused) {
        if (!isPaused){
            if ((animationProgress == 0f) || (animationProgress == 1f) || (animationProgress == 2f)){
                animationProgress = pagerState.currentPage.toFloat()
                while(currentPage < 3) {
                    animationProgress += 0.01f
                    delay(30)
                    if (animationProgress in 0.99f..1.0f) {
                        animationProgress = 1f
                        break
                    }
                    if (animationProgress in 1.99f..2.0f) {
                        animationProgress = 2f
                        break
                    }
                    if (animationProgress >= 2.99f) {
                        break
                    }
                }
            }else{
                while(currentPage < 3) {
                    animationProgress += 0.01f
                    delay(30)
                    if (animationProgress in 0.99f..1.0f) {
                        animationProgress = 1f
                        break
                    }
                    if (animationProgress in 1.99f..2.0f) {
                        animationProgress = 2f
                        break
                    }
                    if (animationProgress >= 2.99f) {
                        break
                    }
                }
            }
        }
    }

    // Memantau perubahan currentPage dan memicu perpindahan pager
    LaunchedEffect(currentPage) {
        snapshotFlow { currentPage }
            .distinctUntilChanged()
            .collect { page ->
                if (page != pagerState.currentPage && page < 3) {
                    pagerState.animateScrollToPage(page)
                }
            }
    }

    // Observe showDialog state to control isPaused
    LaunchedEffect(showDialog) {
        savedProgress = animationProgress // Simpan progress saat ini
        isPaused = showDialog
    }

    if (showDialog) {
        ShowDialogTwoButton(
            onDismissRequest = {showDialog = false},
            label = "Sudah punya rekening BNI?",
            description = "Kamu perlu punya rekening BNI untuk daftar ke wondr business.",
            textBtnOne = "Sudah Punya",
            textBtnTwo = "Buka Rekening via wondr by BNI",
            btnActionOne = {onClickHaveAcc()},
            btnActionTwo = {onClickNotHaveAcc()}
        )
    }

    // Tampilan Onboarding
    Scaffold(
        containerColor = Color.White,

        topBar = {
            ShowTopBar(
                onlyLogo = true,
                imgLeading = painterResource(id = R.drawable.logo_wfb_orange)
            )
        },

        contentWindowInsets = WindowInsets(left = 0.dp, right = 0.dp, top = 0.dp, bottom = 0.dp),

        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(165.dp),
                containerColor = Color.Transparent
            ) {
                DoubleButton(
                    modifier = Modifier.padding(24.dp),
                    textBtnOne = "Daftar Jadi Merchant",
                    colorsBtnOne = ButtonDefaults.buttonColors(containerColor = listColor.SkyBlue),
                    textBtnTwo = "Masuk",
                    onclickBtnTwo = { showDialog = true },
                    borderBtnTwo = BorderStroke(1.dp, listColor.SonicSilver),
                    colorsBtnTwo = ButtonDefaults.buttonColors(containerColor = Color.White)
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { offset ->
                            val screenWidth = size.width
                            val tapAreaWidth =
                                screenWidth * 0.15 // 25% dari layar untuk masing-masing sisi
                            if (offset.x < tapAreaWidth) {
                                // Tap di sisi kiri
                                if (pagerState.currentPage > 0) {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                    }
                                }
                            } else if (offset.x > screenWidth - tapAreaWidth) {
                                // Tap di sisi kanan
                                if (pagerState.currentPage < pagerState.pageCount - 1) {
                                    coroutineScope.launch {
                                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                    }
                                }
                            }
                        },
                        onLongPress = {
                            // Tahan layar lebih lama dari 1 detik untuk pause progress bar
                            savedProgress = animationProgress // Simpan progress saat ini
                            isPaused = true // Pause progress bar saat layar ditekan
                        },
                        onPress = {
                            tryAwaitRelease()
                            // Ketika user melepas layar, lanjutkan progress
                            isPaused = false
                        },
                    )
                },
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                // Indikator dash progress bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(listColor.AeroBlue)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(
                                        when {
                                            index < currentPage -> 1f
                                            index == currentPage -> currentProgress
                                            else -> 0f
                                        }
                                    )
                                    .background(listColor.keppel)
                            )
                        }
                    }
                }

                HorizontalPager(
                    state = pagerState,
                    userScrollEnabled = false // Menonaktifkan slide
                ) { pageIndex ->
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        when (pageIndex) {
                            0 -> {
                                ComponentCardPager(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 24.dp),
                                    vertical = Arrangement.spacedBy(24.dp),
                                    horizontal = Alignment.CenterHorizontally,
                                    painter = painterResource(id = R.drawable.img_onboard_1),
                                    textHeadline1 = "Selamat datang di wondr business",
                                    textbody = "Kami siap menjadi teman setia untuk bisnis kamu."
                                )
                            }

                            1 -> {
                                ComponentCardPager(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 24.dp),
                                    vertical = Arrangement.spacedBy(24.dp),
                                    horizontal = Alignment.CenterHorizontally,
                                    painter = painterResource(id = R.drawable.img_onboard_2),
                                    textHeadline1 = "Terima pembayaran QRIS secara mudah",
                                    textbody = "Buat QRIS untuk ditempel di toko atau tentukan nominal khusus untuk langsung di-scan pelanggan."
                                )
                            }

                            2 -> {
                                ComponentCardPager(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 24.dp),
                                    vertical = Arrangement.spacedBy(24.dp),
                                    horizontal = Alignment.CenterHorizontally,
                                    painter = painterResource(id = R.drawable.img_onboard_3),
                                    textHeadline1 = "Pantau performa bisnis kamu",
                                    textbody = "Dapatkan notifikasi real-time dan download laporan transaksi agar bisa terus pantau performa bisnismu."
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}