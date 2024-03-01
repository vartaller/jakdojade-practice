package com.volodymyr.provider

interface NavigationProvider {
    fun navigateUp()
    fun navigateToUserTicket(ticketId: Int)
    fun navigateToStoreTicket(ticketId: Int)
}