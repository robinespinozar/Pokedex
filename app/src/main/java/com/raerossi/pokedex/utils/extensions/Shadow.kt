package com.raerossi.pokedex.utils.extensions

import android.graphics.BlurMaskFilter
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.smallShadow(): Modifier {
    return this.shadow(
        color = Color.White,
        alpha = 0.15f,
        blurRadius = 32.dp,
        offsetY = 24.dp,
        spread = 15f.dp
    )
}

fun Modifier.mediumShadow(): Modifier {
    return this.shadow(
        color = Color.White,
        alpha = 0.15f,
        blurRadius = 48.dp,
        offsetY = 32.dp,
        spread = 20f.dp
    )
}

fun Modifier.largeShadow(): Modifier {
    return this.shadow(
        color = Color.White,
        alpha = 0.15f,
        blurRadius = 56.dp,
        offsetY = 48.dp,
        spread = 25f.dp
    )
}

fun Modifier.navigationShadow(): Modifier {
    return this.shadow(
        color = Color.Black,
        alpha = 0.15f,
        blurRadius = 4.dp,
        offsetX = 1.dp,
        offsetY =(-1.5).dp
    )
}


fun Modifier.shadow(
    color: Color = Color.White,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 32.dp,
    offsetY: Dp = 24.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 3f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = Color(color.red, color.green, color.blue, alpha).toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)
