package com.volodymyr.list

import com.volodymyr.data.TicketType
import com.volodymyr.data.UserTicket

data class ListPageUiState(
    val ticketsTab: TicketsTab = TicketsTab.STORE,
    val ticketsType: TicketsType = TicketsType.REDUCED,
    val storeTicketsReduced: List<TicketCard> = StoreTicketCardReduced.values().toList(),
    val storeTicketsRegular: List<TicketCard> = StoreTicketCardRegular.values().toList(),
    val allUserTickets: List<UserTicket> = emptyList(),
)

enum class TicketsTab(val tabId: Int) {
    STORE(R.string.screen_tickets_selector_store),
    USERS(R.string.screen_tickets_selector_users_tickets),
}

enum class TicketsType(val typeId: Int) {
    REDUCED(R.string.screen_store_tickets_reduced),
    REGULAR(R.string.screen_store_tickets_regular),
}

enum class TicketsTerm(val termId: Int) {
    MINUTE(R.string.tickets_term_minutes),
    HOUR(R.string.tickets_term_hours),
    GROUP(R.string.tickets_term_group),
}

interface TicketCard {
    val ticketType: TicketType
    val provider: Int
    val scope: Int
    val time: Int
    val unit: Int
    val price: Int
    val currency: Int
    val scopeFormat: Int
}

enum class StoreTicketCardReduced(
    override val ticketType: TicketType = TicketType.REDUCED,
    override val provider: Int,
    override val scope: Int,
    override val time: Int,
    override val unit: Int,
    override val price: Int,
    override val currency: Int,
    override val scopeFormat: Int,
) : TicketCard {
    SHORT_TERM_REDUCED(
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_20,
        unit = R.string.ticket_unit_minutes,
        price = R.string.price_reduced_minutes_short,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
    ),
    MIDDLE_TERM_REDUCED(
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_60,
        unit = R.string.ticket_unit_minutes_or_trip,
        price = R.string.price_reduced_minutes_middle,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
    ),
    LONG_TERM_REDUCED(
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_90,
        unit = R.string.ticket_unit_minutes,
        price = R.string.price_reduced_minutes_long,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
    ),
}

enum class StoreTicketCardRegular(
    override val ticketType: TicketType = TicketType.REGULAR,
    override val provider: Int,
    override val scope: Int,
    override val time: Int,
    override val unit: Int,
    override val price: Int,
    override val currency: Int,
    override val scopeFormat: Int,
) : TicketCard {
    SHORT_TERM_REGULAR(
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_20,
        unit = R.string.ticket_unit_minutes,
        price = R.string.price_regular_minutes_short,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
    ),
    MIDDLE_TERM_REGULAR(
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_60,
        unit = R.string.ticket_unit_minutes_or_trip,
        price = R.string.price_regular_minutes_middle,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
    ),
    LONG_TERM_REGULAR(
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_90,
        unit = R.string.ticket_unit_minutes,
        price = R.string.price_regular_minutes_long,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
    ),
}