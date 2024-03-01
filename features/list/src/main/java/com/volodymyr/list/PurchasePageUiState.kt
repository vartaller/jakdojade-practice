package com.volodymyr.list

import com.volodymyr.data.StoreTicket
import java.util.Date

data class PurchasePageUiState(
    val timeCounter: Int = 30,
    val timeDeadline: Date = Date(System.currentTimeMillis() + timeCounter * 1000L),
    val ticketAmount: Int = 1,
    val ticket: StoreTicket? = null,
)

sealed class PurchasePageEffect {
    class GoBack(val toReturn: Boolean) : PurchasePageEffect()
}