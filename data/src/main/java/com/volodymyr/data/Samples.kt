package com.volodymyr.data

import java.util.Date

fun sampleUserTicketsData(): List<UserTicket> {
    val sample1 = UserTicket(
        id = 1,
        storeTicketId = 1,
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
        storeTicketId = 2,
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

fun sampleStoreTicketsData(): List<StoreTicket> {

    return listOf(
    StoreTicket(
        id = 1,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.SHORT,
        unit = UnitDb.MINUTE,
        price = PriceDb.REDUCED_MINS_SHORT,
        termGroup = TicketsTermGroup.TIME_LIMIT
    ),
    StoreTicket(
        id = 2,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.MIDDLE,
        unit = UnitDb.MINUTE_OR_TRIP,
        price = PriceDb.REDUCED_MINS_MIDDLE,
        termGroup = TicketsTermGroup.TIME_LIMIT
    ),
    StoreTicket(
        id = 3,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.LONG,
        unit = UnitDb.MINUTE,
        price = PriceDb.REDUCED_MINS_LONG,
        termGroup = TicketsTermGroup.TIME_LIMIT
    ),
    StoreTicket(
        id = 4,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.DISTRICT,
        duration = DurationDb.ONE_DAY,
        unit = UnitDb.HOUR_DISTRICT,
        price = PriceDb.REDUCED_HOURS_SHORT_DISTRICT,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 5,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.ONE_DAY,
        unit = UnitDb.HOUR_CITY,
        price = PriceDb.REDUCED_HOURS_SHORT_CITY,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 6,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.DISTRICT,
        duration = DurationDb.THREE_DAYS,
        unit = UnitDb.DAY_DISTRICT,
        price = PriceDb.REDUCED_WEEK_DISTRICT,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 7,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.SEVEN_DAYS,
        unit = UnitDb.DAY_CITY,
        price = PriceDb.REDUCED_WEEK_CITY,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 8,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.TWO_DAYS,
        unit = UnitDb.HOUR,
        price = PriceDb.REDUCED_HOURS_MIDDLE,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 9,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.THREE_DAYS,
        unit = UnitDb.HOUR,
        price = PriceDb.REDUCED_HOURS_LONG,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 10,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.DISTRICT,
        duration = DurationDb.GROUP,
        unit = UnitDb.GROUP_TRIP,
        price = PriceDb.REDUCED_GROUP_DISTRICT,
        termGroup = TicketsTermGroup.GROUP
    ),
    StoreTicket(
        id = 11,
        type = TicketTypeDb.REDUCED,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.GROUP,
        unit = UnitDb.GROUP_TRIP,
        price = PriceDb.REDUCED_GROUP_CITY,
        termGroup = TicketsTermGroup.GROUP
    ),
    StoreTicket(
        id = 12,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.SHORT,
        unit = UnitDb.MINUTE,
        price = PriceDb.REGULAR_MINS_SHORT,
        termGroup = TicketsTermGroup.TIME_LIMIT
    ),
    StoreTicket(
        id = 13,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.MIDDLE,
        unit = UnitDb.MINUTE_OR_TRIP,
        price = PriceDb.REGULAR_MINS_MIDDLE,
        termGroup = TicketsTermGroup.TIME_LIMIT
    ),
    StoreTicket(
        id = 14,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.LONG,
        unit = UnitDb.MINUTE,
        price = PriceDb.REGULAR_MINS_LONG,
        termGroup = TicketsTermGroup.TIME_LIMIT
    ),
    StoreTicket(
        id = 15,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.DISTRICT,
        duration = DurationDb.ONE_DAY,
        unit = UnitDb.HOUR_DISTRICT,
        price = PriceDb.REGULAR_HOURS_SHORT_DISTRICT,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 16,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.ONE_DAY,
        unit = UnitDb.HOUR_CITY,
        price = PriceDb.REGULAR_HOURS_SHORT_CITY,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 17,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.DISTRICT,
        duration = DurationDb.THREE_DAYS,
        unit = UnitDb.DAY_DISTRICT,
        price = PriceDb.REGULAR_WEEK_DISTRICT,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 18,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.SEVEN_DAYS,
        unit = UnitDb.DAY_CITY,
        price = PriceDb.REGULAR_WEEK_CITY,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 19,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.TWO_DAYS,
        unit = UnitDb.HOUR,
        price = PriceDb.REGULAR_HOURS_MIDDLE,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 20,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.THREE_DAYS,
        unit = UnitDb.HOUR,
        price = PriceDb.REGULAR_HOURS_LONG,
        termGroup = TicketsTermGroup.SHORT_TERM
    ),
    StoreTicket(
        id = 21,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.WEEKEND,
        unit = UnitDb.FAMILY_TRIP,
        price = PriceDb.REGULAR_FAMILY_WEEKEND,
        termGroup = TicketsTermGroup.GROUP
    ),
    StoreTicket(
        id = 22,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.DISTRICT,
        duration = DurationDb.GROUP,
        unit = UnitDb.GROUP_TRIP,
        price = PriceDb.REGULAR_GROUP_DISTRICT,
        termGroup = TicketsTermGroup.GROUP
    ),
    StoreTicket(
        id = 23,
        type = TicketTypeDb.REGULAR,
        provider = ProviderDb.ZTP_KRK,
        scope = ScopeDb.CITY,
        duration = DurationDb.GROUP,
        unit = UnitDb.GROUP_TRIP,
        price = PriceDb.REGULAR_GROUP_CITY,
        termGroup = TicketsTermGroup.GROUP
    ))
}