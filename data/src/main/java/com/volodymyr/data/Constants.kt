package com.volodymyr.data


enum class ScreenTitle(val titleId: Int) {
    TICKETS(R.string.screen_title_tickets),
    TICKET(R.string.screen_title_purchased_ticked),
}

enum class TicketType(val typeId: Int) {
    REDUCED(R.string.screen_store_ticket_reduced),
    REGULAR(R.string.screen_store_ticket_regular),
}