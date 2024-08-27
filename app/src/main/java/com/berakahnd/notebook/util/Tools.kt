package com.berakahnd.notebook.util

import androidx.compose.ui.graphics.Color


object Tools {
    val lightColors = listOf(
        "#F0F8FF",
        "#FAEBD7",
        "#F5F5DC",
        "#FFF8DC",
        "#E0FFFF",
        "#F0FFF0",
        "#F5FFFA",
        "#FFFFF0",
        "#FFF0F5",
        "#FDF5E6"
    )
    fun colorFromHex(hex: String): Color {
        return Color(android.graphics.Color.parseColor(hex))
    }
}