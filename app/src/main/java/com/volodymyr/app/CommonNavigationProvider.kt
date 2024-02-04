package com.volodymyr.app

import androidx.navigation.NavController;
import com.ramcosta.composedestinations.navigation.navigateTo
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ticket.destinations.TicketPageDestination

class CommonNavigationProvider(
    private val navController: NavController
) : NavigationProvider {

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun navigateToTicket(id: Int) {
        navController.navigateTo(TicketPageDestination(id))
    }
}