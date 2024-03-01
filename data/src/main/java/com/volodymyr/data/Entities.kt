package com.volodymyr.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
@Entity(tableName = "store_ticket")
data class StoreTicket(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: TicketTypeDb,
    val provider: ProviderDb,
    val scope: ScopeDb,
    val duration: DurationDb,
    val unit: UnitDb,
    val price: PriceDb,
    val termGroup: TicketsTermGroup,
)

@Entity(tableName = "users_ticket")
data class UserTicket(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val type: TicketTypeDb,
    val provider: ProviderDb,
    val scope: ScopeDb,
    val duration: DurationDb,
    val unit: UnitDb,
    val price: PriceDb,
    val dateStamp: Date,
)


enum class TicketTypeDb(val type: String) {
    REDUCED("Льготный"),
    REGULAR("Обычный"),
}

enum class TicketTimeDb(val type: String) {
    REDUCED("Льготный"),
    REGULAR("Обычный"),
}

enum class ProviderDb(val type: String) {
    ZTP_KRK("ZTP w Krakowie"),
}

enum class ScopeDb(val type: String) {
    COUNTRY("I+II+III+IV"),
    CITY("I+II+III"),
    DISTRICT("I"),
}

enum class DurationDb(val type: String) {
    SHORT("20"),
    MIDDLE("60"),
    LONG("90"),
    ONE_DAY("24"),
    SEVEN_DAYS("7"),
    TWO_DAYS("48"),
    THREE_DAYS("72"),
    GROUP("GROUP"),
    WEEKEND("WEEKEND"),
}

enum class UnitDb(val type: String) {
    MINUTE("minut"),
    MINUTE_OR_TRIP("minut or single trip"),
    HOUR("hour"),
    HOUR_DISTRICT("hour (${ScopeDb.DISTRICT.type})"),
    HOUR_CITY("hour (${ScopeDb.CITY.type})"),
    DAY_DISTRICT("day (${ScopeDb.DISTRICT.type})"),
    DAY_CITY("day (${ScopeDb.CITY.type})"),
    GROUP_TRIP("single trip, up to 20 people"),
    FAMILY_TRIP("family ticket"),
}

enum class PriceDb(val type: String) {
    REDUCED_MINS_SHORT("2.00"),
    REDUCED_MINS_MIDDLE("3.00"),
    REDUCED_MINS_LONG("4.00"),
    REGULAR_MINS_SHORT("4.00"),
    REGULAR_MINS_MIDDLE("6.00"),
    REGULAR_MINS_LONG("8.00"),

    REDUCED_HOURS_SHORT_DISTRICT("8.50"),
    REDUCED_HOURS_SHORT_CITY("11.00"),
    REDUCED_HOURS_MIDDLE("17.50"),
    REDUCED_HOURS_LONG("25.00"),
    REDUCED_WEEK_DISTRICT("28.00"),
    REDUCED_WEEK_CITY("34.00"),
    REGULAR_HOURS_SHORT_DISTRICT("17.00"),
    REGULAR_HOURS_SHORT_CITY("22.00"),
    REGULAR_HOURS_MIDDLE("35.00"),
    REGULAR_HOURS_LONG("50.00"),
    REGULAR_WEEK_DISTRICT("56.00"),
    REGULAR_WEEK_CITY("68.00"),

    REDUCED_GROUP_DISTRICT("25.00"),
    REDUCED_GROUP_CITY("30.00"),

    REGULAR_FAMILY_WEEKEND("25.00"),
    REGULAR_GROUP_DISTRICT("50.00"),
    REGULAR_GROUP_CITY("60.00"),
}

enum class TicketsTermGroup(val termId: Int) {
    TIME_LIMIT(R.string.tickets_term_minutes),
    SHORT_TERM(R.string.tickets_term_hours),
    GROUP(R.string.tickets_term_group),
}
