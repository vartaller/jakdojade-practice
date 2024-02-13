package com.volodymyr.app

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.volodymyr.list.ListNavGraph
import com.volodymyr.ticket.TicketNavGraph

object RootNavGraph : NavGraphSpec {
    override val route = "root"

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val startRoute = ListNavGraph

    override val nestedNavGraphs = listOf(
        ListNavGraph,
        TicketNavGraph
    )
}