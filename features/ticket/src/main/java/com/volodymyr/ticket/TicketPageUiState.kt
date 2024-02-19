package com.volodymyr.ticket

import com.volodymyr.data.ScreenTitle
import com.volodymyr.data.TicketType

data class TicketPageUiState(
    val screenTitle: ScreenTitle = ScreenTitle.TICKET,
    val ticketType: TicketType = TicketType.REDUCED,
    val ticketValues: List<TicketDataModel> = emptyList(),
    val ticketTime: String = "",
    val ticketScope: String = "",
    val ticketTimeUnit: String = "",
    val imgArrowBackId: ImgArrowBack = ImgArrowBack.REGULAR,
    val textBack: TextBack = TextBack.RU,
    val textScopeFormat: TextScopeFormat = TextScopeFormat.PL,
    val textTicketActive: TextTicketActive = TextTicketActive.RU,
    val textTicketInactive: TextTicketInactive = TextTicketInactive.RU,
    val textQrDescription: TextQrCodeDescription = TextQrCodeDescription.EN,
    val textQrScale: TextQrCodeScale = TextQrCodeScale.RU,
)
data class TicketDataModel(val title: TicketDataFieldNames, val value: String)

enum class TicketDataFieldNames(
    val nameId: Int
){
    VALID_FROM(R.string.ticket_field_from),
    VALID_TO(R.string.ticket_field_to),
    PHONE(R.string.ticket_field_phone),
    VEHICLE(R.string.ticket_field_vehicle),
    PRICE(R.string.ticket_field_price),
    PROVIDER(R.string.ticket_field_provider),
    CODE(R.string.ticket_field_code),
}

data class TicketDataValues(
    val validFrom: String = "16.01.2024 11:45:47",
    val validTo: String = "16.01.2024 12:05:47",
    val phone: String = "49 765765765",
    val vehicle: String = "HL417",
    val price: String = "4,00 zl",
    val provider: String = "ZTP w Krakowie",
    val code: String = "33A52GQ9",
    val type: String = "NORMALNY",
    val time: String = "20",
    val unit: String = "minut",
    val scope: String = "I+II+III",
)

enum class ImgArrowBack(val imgId: Int) {
    REGULAR(R.drawable.arrow_back),
}

enum class TextBack(val textId: Int) {
    RU(R.string.screen_ticket_image_arrow_back),
}

enum class TextScopeFormat(val textId: Int) {
    PL(R.string.ticket_domain),
}

enum class TextTicketActive(val textId: Int) {
    RU(R.string.ticket_status_active),
}

enum class TextTicketInactive(val textId: Int) {
    RU(R.string.ticket_status_inactive),
}

enum class TextQrCodeDescription(val textId: Int) {
    EN(R.string.screen_ticket_image_qr),
}

enum class TextQrCodeScale(val textId: Int) {
    RU(R.string.screen_ticket_qr_scale),
}
