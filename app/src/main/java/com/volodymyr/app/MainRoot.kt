package com.volodymyr.app

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.volodymyr.ui.theme.MyApplicationTheme

@Composable
fun MainRoot(finish: () -> Unit) {
    val navController = rememberNavController()

    val currentBackStackEntryAsState by navController.currentBackStackEntryAsState()
    val destination = currentBackStackEntryAsState?.destination?.route
        ?: RootNavGraph.startRoute.startRoute.route

    if (destination == RootNavGraph.startRoute.startRoute.route) {
        BackHandler { finish() }
    }


    MyApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DestinationsNavHost(
                navController = navController,
                navGraph = RootNavGraph,
                dependenciesContainerBuilder = {
                    dependency(CommonNavigationProvider(navController))
                }
            )
        }
    }
}