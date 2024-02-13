package com.volodymyr.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    headlineMedium = TextStyle(
        textDecoration = TextDecoration.Underline,
        fontSize = 16.sp
    ),
    headlineLarge = TextStyle(
        fontSize = 20.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontSize = 18.sp,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 80.sp
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
    displaySmall = TextStyle(
        fontSize = 8.sp, letterSpacing = 2.sp
    ),
    displayMedium = TextStyle(
        fontSize = 28.sp, fontWeight = FontWeight.Bold
    )
)