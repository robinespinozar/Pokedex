package com.raerossi.pokedex.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.raerossi.pokedex.R

val satoshiFamily = FontFamily(
    Font(R.font.satoshi_black, FontWeight.Black),
    Font(R.font.satoshi_blackitalic, FontWeight.Black, FontStyle.Italic),
    Font(R.font.satoshi_bold, FontWeight.Bold),
    Font(R.font.satoshi_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.satoshi_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.satoshi_light, FontWeight.Light),
    Font(R.font.satoshi_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.satoshi_medium, FontWeight.Medium),
    Font(R.font.satoshi_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.satoshi_regular, FontWeight.Normal)
)

val generalSansFamily = FontFamily(
    Font(R.font.generalsans_bold, FontWeight.Bold),
    Font(R.font.generalsans_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.generalsans_extralight, FontWeight.ExtraLight),
    Font(R.font.generalsans_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.generalsans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.generalsans_light, FontWeight.Light),
    Font(R.font.generalsans_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.generalsans_medium, FontWeight.Medium),
    Font(R.font.generalsans_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.generalsans_regular, FontWeight.Normal),
    Font(R.font.generalsans_semibold, FontWeight.SemiBold),
    Font(R.font.generalsans_semibolditalic, FontWeight.SemiBold, FontStyle.Italic)
)

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        letterSpacing = (-0.25).sp
    ),
    displayMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        letterSpacing = 0.sp
    ),
    displaySmall = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = satoshiFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelLarge = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = generalSansFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
)