package com.volodymyr.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography


@Destination(start = true)
@Composable
fun ListPage(
    navigator: NavigationProvider,
    viewModel: ListPageViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    ListPageEffect(
        effect = effect,
        navigator = navigator,
        viewModel = viewModel
    )

    val selectedTicketsTab = state.ticketsTab.tabId
    val selectedTicketsType = state.ticketsType.typeId
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = MainColorScheme.onTertiary),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            state,
            stringResource(id = R.string.screen_tickets_title),
            balance = 4.80,
            currency = stringResource(
                id = R.string.currency_pln
            )
        )
        ListSelector(selectedTicketsTab = selectedTicketsTab) { newTicketsTab ->
            viewModel.updateTabState(newTicketsTab = newTicketsTab)
        }
        Column(
            modifier = Modifier
                .background(color = MainColorScheme.tertiary),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (selectedTicketsTab == TicketsTab.STORE.tabId) {
                ListStoreTickets(
                    state = state,
                    selectedTicketsType = selectedTicketsType
                )
            } else {
                ListUsersTickets(state = state, navigator = navigator)
            }
        }
    }
}

@Composable
fun ListPageEffect(
    effect: ListPageEffect?,
    navigator: NavigationProvider,
    viewModel: ListPageViewModel
) {
    when (effect) {
        is ListPageEffect.GoToTicketPurchase -> navigator.navigateToStoreTicket(effect.ticketId)
        null -> Unit
    }
    viewModel.clearEffect()
}

@Composable
fun Title(
    state: ListPageUiState,
    title: String,
    balance: Double,
    currency: String,
) {
    val imgProfile = painterResource(R.drawable.profile)
    val balanceFormatted = String.format(
        stringResource(
            id = R.string.balance_format
        ), balance
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MainColorScheme.primary
            )
            .padding(bottom = 12.dp),
    ) {
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            color = MainColorScheme.onPrimary,
            textAlign = TextAlign.Left,
            style = Typography.titleMedium,
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd),
        ) {
            Text(
                text = stringResource(
                    id = R.string.price_format,
                    balanceFormatted,
                    currency
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = MainColorScheme.tertiaryContainer)
                    .padding(6.dp),
                color = MainColorScheme.onPrimary,
                textAlign = TextAlign.Left,
                style = Typography.bodyMedium,
            )
            Image(
                painter = imgProfile,
                contentDescription = stringResource(id = R.string.screen_tickets_image_profile),
                modifier = Modifier
                    .padding(8.dp, 12.dp, 8.dp, 12.dp)
                    .size(20.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
// ------------------ use to close app by clicking back
//                    .clickable { pressOnBack.invoke() }
            )
        }
    }
}

@Composable
fun ListSelector(
    selectedTicketsTab: Int,
    onTabSelected: (TicketsTab) -> Unit
) {
    Row(
        modifier = Modifier
            .background(
                color = MainColorScheme.primary
            )
            .padding(36.dp, 0.dp, 36.dp, 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TicketsTab.values().forEach { item ->
            Text(
                text = stringResource(id = item.tabId),
                modifier = Modifier
                    .drawBehind {

                        val strokeWidth = 5F
                        val y = size.height - strokeWidth

                        drawLine(
                            color = if (selectedTicketsTab == item.tabId) {
                                MainColorScheme.outline
                            } else {
                                Color.Transparent
                            },
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
                    .padding(8.dp)
                    .clickable {
                        onTabSelected(item)
                    },
                color = if (selectedTicketsTab == item.tabId) {
                    MainColorScheme.outline
                } else {
                    Color.Gray
                },
                style = Typography.bodyLarge,
            )
        }
    }
}

