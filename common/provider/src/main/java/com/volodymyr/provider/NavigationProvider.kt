package com.volodymyr.provider

interface NavigationProvider {
    fun navigateUp()
    fun navigateToTicket(resId: Int)
}