package com.bni.wfb.pages.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bni.wfb.ui.theme.listColor
import kotlinx.coroutines.delay

@Composable
fun CountdownTimer(
    timeInSeconds: Int,
    modifier: Modifier = Modifier,
    onTimeFinished: () -> Unit = {}
) {
    var currentTime by remember { mutableStateOf(timeInSeconds) }
    var textTimer by remember {
        mutableStateOf("")
    }
    var timerSecond by remember {
        mutableIntStateOf(30)
    }
    val sweepAngle by animateFloatAsState(
        targetValue = 360f * (currentTime / timeInSeconds.toFloat()),
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    LaunchedEffect(currentTime) {
        if (currentTime > 0) {
            delay(1000L)
            if (currentTime <= 59){
                textTimer = currentTime.toString()
                currentTime--
            }else{
                textTimer = "1:$timerSecond"
                currentTime--
                timerSecond--
            }
        } else {
            textTimer = currentTime.toString()
            onTimeFinished()
        }
    }

    Row (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Canvas(modifier = Modifier
            .size(16.dp)
            .border(
                BorderStroke(
                    1.5.dp,
                    listColor.keppel
                ),
                RoundedCornerShape(32.dp)
            )
        ) {
            drawCircle(
                color = Color.Transparent,
                radius = size.minDimension / 2,
                center = center
            )

            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(center.x, center.y)
                arcTo(
                    rect = Rect(Offset.Zero, size),
                    startAngleDegrees = -90f,
                    sweepAngleDegrees = sweepAngle,
                    forceMoveTo = false
                )
                lineTo(center.x, center.y)
                close()
            }

            clipPath(path) {
                drawCircle(
                    color = listColor.keppel,
                    radius = size.minDimension / 2,
                    center = center
                )
            }
        }
        Text(
            text = textTimer,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600,
            color = listColor.keppel,
        )
    }
}