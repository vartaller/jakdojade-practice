package com.volodymyr.list

import com.volodymyr.data.ScreenTitle
import com.volodymyr.data.TicketType

data class ListPageUiState(
    val ticketsTab: TicketsTab = TicketsTab.STORE,
    val ticketsType: TicketsType = TicketsType.REDUCED,
    val ticketTypeReduced: TicketType = TicketType.REDUCED,
    val ticketTypeRegular: TicketType = TicketType.REGULAR,
    val balance: Double = 0.00,
    val imgProfile: ImgProfile = ImgProfile.REGULAR,
    val imgCalendar: ImgUsersTicket = ImgUsersTicket.CALENDAR,
    val imgPrice: ImgUsersTicket = ImgUsersTicket.TICKET,
    val imgDescription: ImgDescription = ImgDescription.PROFILE,
    val currency: Currency = Currency.PLN,
    val balanceFormat: Format = Format.TWO_DECIMALS,
    val priceFormat: Format = Format.PRICE_CURRENCY,
    val scopeFormat: Format = Format.PRICE_CURRENCY,
    val screenTitle: ScreenTitle = ScreenTitle.TICKETS,
    val storeTicketsReduced: List<TicketCard> = StoreTicketCardReduced.values().toList(),
    val storeTicketsRegular: List<TicketCard> = StoreTicketCardRegular.values().toList(),
    val userTickets: List<UsersTicketCard> = UsersTicketCard.values().toList(),
    val ticketSubmitText: UsersTicketText = UsersTicketText.SUBMIT,
    val ticketFromText: UsersTicketText = UsersTicketText.FROM,
    val ticketDateText: UsersTicketText = UsersTicketText.DATE,
    val ticketByAgainText: UsersTicketText = UsersTicketText.BUY_AGAIN,
    val ticketSeeMoreText: UsersTicketText = UsersTicketText.SEE_MORE,
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

enum class ImgProfile(val imgId: Int) {
    REGULAR(R.drawable.profile),
}

enum class ImgUsersTicket(val imgId: Int) {
    CALENDAR(R.drawable.calendar),
    TICKET(R.drawable.price),
}

enum class Currency(val currencyId: Int) {
    PLN(R.string.currency_pln),
}

enum class Format(val formatId: Int) {
    TWO_DECIMALS(R.string.balance_format),
    PRICE_CURRENCY(R.string.price_format),
    SCOPE_ZONE(R.string.ticket_scope_format),
}

enum class ImgDescription(val descriptionId: Int) {
    PROFILE(R.string.screen_tickets_image_profile),
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

enum class UsersTicketText(val textId: Int){
    SUBMIT(R.string.ticket_submit),
    FROM(R.string.ticket_field_from),
    DATE(R.string.ticket_date),
    BUY_AGAIN(R.string.ticket_buy_again),
    SEE_MORE(R.string.ticket_see_more)
}

class Date(
    val day: String,
    val date: String,
    val time: String,
)

enum class UsersTicketCard(
    override val ticketType: TicketType,
    override val provider: Int,
    override val scope: Int,
    override val time: Int,
    override val unit: Int,
    override val price: Int,
    override val currency: Int,
    override val scopeFormat: Int,
    val date: Date,
) : TicketCard {
    SAMPLE1(
        ticketType = TicketType.REDUCED,
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_20,
        unit = R.string.ticket_unit_minutes,
        price = R.string.price_regular_minutes_short,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
        date = Date(day = "wtorek", date = "16.01.2024", time = "11:45")
    ),
    SAMPLE2(
        ticketType = TicketType.REDUCED,
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_60,
        unit = R.string.ticket_unit_minutes_or_trip,
        price = R.string.price_regular_minutes_middle,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
        date = Date(day = "wtorek", date = "16.01.2024", time = "11:45")
    ),
    SAMPLE3(
        ticketType = TicketType.REDUCED,
        provider = R.string.ticket_provider,
        scope = R.string.ticket_scope_country,
        time = R.string.ticket_duration_90,
        unit = R.string.ticket_unit_minutes,
        price = R.string.price_regular_minutes_long,
        currency = R.string.currency_pln,
        scopeFormat = R.string.ticket_scope_format,
        date = Date(day = "wtorek", date = "16.01.2024", time = "11:45")
    ),
}
