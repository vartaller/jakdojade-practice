package com.volodymyr.list

import com.volodymyr.data.TicketType
import com.volodymyr.data.UserTicket
import java.util.Date

data class TicketPageUiState(
    val ticketType: TicketType = TicketType.REDUCED,
    val ticket: UserTicket? = null,
    val dateFrom: String = "",
    val dateTo: String = "",
    val isActive: Boolean = true,
    val ticketDeadline: Date = Date(),
    val ticketDataFieldNames: List<TicketDataFieldNames> = TicketDataFieldNames.values().toList(),
)

enum class TicketDataFieldNames(
    val nameId: Int
) {
    VALID_FROM(R.string.ticket_field_from),
    VALID_TO(R.string.ticket_field_to),
    PHONE(R.string.ticket_field_phone),
    VEHICLE(R.string.ticket_field_vehicle),
    PRICE(R.string.ticket_field_price),
    PROVIDER(R.string.ticket_field_provider),
    CODE(R.string.ticket_field_code),
}
