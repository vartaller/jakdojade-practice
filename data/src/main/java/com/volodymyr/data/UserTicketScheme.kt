package com.volodymyr.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

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
fun sampleData(): List<UserTicket> {
    val sample1 = UserTicket(
        id = 1,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.LONG,
        unit = UnitDb.MINUTE_OR_TRIP,
        price = PriceDb.REDUCED_MINS_LONG,
        dateStamp = Date(2023 - 1900, 11, 21, 11, 42)
    )
    val sample2 = UserTicket(
        id = 2,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.SHORT,
        unit = UnitDb.MINUTE,
        price = PriceDb.REGULAR_MINS_SHORT,
        dateStamp = Date(2023 - 1900, 12, 22, 10, 33)
    )
    return listOf(sample1, sample2)
}

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
}

enum class DurationDb(val type: String) {
    SHORT("20"),
    MIDDLE("60"),
    LONG("90"),
}

enum class UnitDb(val type: String) {
    MINUTE("minut"),
    MINUTE_OR_TRIP("minut or single trip"),
}

enum class PriceDb(val type: Double) {
    REDUCED_MINS_SHORT(2.00),
    REDUCED_MINS_MIDDLE(3.00),
    REDUCED_MINS_LONG(4.00),
    REGULAR_MINS_SHORT(4.00),
    REGULAR_MINS_MIDDLE(6.00),
    REGULAR_MINS_LONG(8.00),
}
