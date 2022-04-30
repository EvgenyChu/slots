package com.template.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.template.R

val openSansBold = FontFamily(
    Font(R.font.open_sans_bold)
)

val openSansMedium = FontFamily(
    Font(R.font.open_sans_medium)
)

fun MyTypography(onPrimary: Color, onBackground: Color, onSecondary: Color) = Typography(
    h1 = TextStyle(
        fontFamily = openSansBold,

        fontSize = 96.sp
    ),
    h2 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 60.sp
    ),
    h3 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 48.sp
    ),
    h4 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 21.sp,
        color = onBackground
    ),
    h5 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 17.sp,
        color = onPrimary
    ),
    h6 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 21.sp,
        color = onPrimary
    ),
    subtitle1 = TextStyle(
        fontFamily = openSansMedium,
        fontSize = 17.sp,
        color = onBackground
    ),
    subtitle2 = TextStyle(
        fontFamily = openSansMedium,
        fontSize = 17.sp,
        color = onSecondary
    ),
    body1 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 14.sp,
        color = onPrimary
    ),
    body2 = TextStyle(
        fontFamily = openSansBold,
        fontSize = 14.sp,
        color = onBackground
    ),
    button = TextStyle(
        fontFamily = openSansBold,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = openSansBold,
        fontSize = 14.sp,
        color = onBackground
    ),
    overline = TextStyle(
        fontFamily = openSansMedium,
        fontSize = 17.sp
    )
)