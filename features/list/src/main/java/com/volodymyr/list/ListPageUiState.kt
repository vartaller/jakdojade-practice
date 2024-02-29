package com.volodymyr.list

import com.volodymyr.data.StoreTicket
import com.volodymyr.data.UserTicket

data class ListPageUiState(
    val ticketsTab: TicketsTab = TicketsTab.STORE,
    val ticketsType: TicketsType = TicketsType.REDUCED,
    val allUserTickets: List<UserTicket> = emptyList(),
    val allStoreTickets: List<StoreTicket> = emptyList(),
)

enum class TicketsTab(val tabId: Int) {
    STORE(R.string.screen_tickets_selector_store),
    USERS(R.string.screen_tickets_selector_users_tickets),
}

enum class TicketsType(val typeId: Int) {
    REDUCED(R.string.screen_store_tickets_reduced),
    REGULAR(R.string.screen_store_tickets_regular),
}