package com.volodymyr.app

import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigateTo
import com.volodymyr.destinations.PurchasePageDestination
import com.volodymyr.destinations.TicketPageDestination
import com.volodymyr.provider.NavigationProvider

class CommonNavigationProvider(
    private val navController: NavController
) : NavigationProvider {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToUserTicket(ticketId: Int) {
        navController.navigateTo(TicketPageDestination(ticketId))
    }

    override fun navigateToStoreTicket(ticketId: Int) {
        navController.navigateTo(PurchasePageDestination(ticketId))
    }
}