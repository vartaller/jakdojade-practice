package com.volodymyr.list

data class ListPageUiState(
    val currentTicketsTab: TicketsTab = TicketsTab.STORE_TICKETS,
    val currentTicketsType: TicketsType = TicketsType.REDUCED_TICKET
)

enum class TicketsTab(val tab: Int) {
    STORE_TICKETS(R.string.screen_tickets_selector_store),
    USERS_TICKETS(R.string.screen_tickets_selector_users_tickets),
}

enum class TicketsType(val tab: Int) {
    REDUCED_TICKET(R.string.screen_store_ticket_reduced),
    NORMAL_TICKET(R.string.screen_store_ticket_normal),
}