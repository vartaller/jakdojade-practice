package com.volodymyr.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

data class InfoFieldData(val title: String, val value: String)

val infoFieldsData = listOf(
    InfoFieldData("Wazny od", "16.01.2024 11:45:47"),
    InfoFieldData("Wazny do", "16.01.2024 12:05:47"),
    InfoFieldData("Numer telefonu", "49 765765765"),
    InfoFieldData("Numer boczny pojazdu", "HL417"),
    InfoFieldData("Cena", "4,00 zl"),
    InfoFieldData("Wystawca biletu", "ZTP w Krakowie"),
    InfoFieldData("Kod biletu", "33A52GQ9")
)

// сделать первоначальный экран

class MainActivity : ComponentActivity() {

    private var backPressed = 0L

    private val finish: () -> Unit = {
        finishAndRemoveTask()
        backPressed = System.currentTimeMillis()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainRoot(finish = finish)
        }
    }
}
