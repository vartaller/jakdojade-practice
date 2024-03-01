package com.volodymyr.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.volodymyr.data.DurationDb
import com.volodymyr.data.R
import com.volodymyr.data.StoreTicket
import com.volodymyr.data.TicketTypeDb
import com.volodymyr.data.TicketsTermGroup
import com.volodymyr.data.UnitDb
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography

@Composable
fun ListStoreTickets(
    state: ListPageUiState,
    selectedTicketsType: Int,
    viewModel: ListPageViewModel = hiltViewModel(),
) {
    TicketTypeSelector(selectedTicketsType = selectedTicketsType) { newTicketsType ->
        viewModel.updateTypeState(newTicketsType = newTicketsType)
    }
    TicketsTermGroup.values().forEach { term ->
        TicketGroup(
            viewModel = viewModel,
            state = state,
            title = stringResource(id = term.termId),
            selectedTicketType = selectedTicketsType
        )
        if (term == TicketsTermGroup.values().last()) {
            Spacer(
                modifier = Modifier
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(color = MainColorScheme.onTertiary)
            )
        }
    }
}


@Composable
fun TicketTypeSelector(selectedTicketsType: Int, onTabSelected: (TicketsType) -> Unit) {
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(24.dp))
            .background(color = MainColorScheme.onTertiary),
        horizontalArrangement = Arrangement.Center
    ) {
        TicketsType.values().forEach { item ->
            Text(
                text = stringResource(id = item.typeId),
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(
                        color = if (selectedTicketsType == item.typeId) {
                            MainColorScheme.onPrimary
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable {
                        onTabSelected(item)
                    }
                    .padding(16.dp, 8.dp, 16.dp, 8.dp),
                color = if (selectedTicketsType == item.typeId) {
                    MainColorScheme.surfaceTint
                } else {
                    MainColorScheme.surface
                },
                style = Typography.bodyLarge,
            )
        }
    }
}


@Composable
fun TicketGroup(
    viewModel: ListPageViewModel,
    state: ListPageUiState,
    title: String,
    selectedTicketType: Int,
) {
    val storeTicketsGroup: List<StoreTicket> =
        if (selectedTicketType == R.string.screen_store_tickets_reduced) {
            state.allStoreTickets.filter {
                it.type == TicketTypeDb.REDUCED &&
                        stringResource(id = it.termGroup.termId) == title
            }
        } else {
            state.allStoreTickets.filter {
                it.type == TicketTypeDb.REGULAR &&
                        stringResource(id = it.termGroup.termId) == title
            }
        }
    val ticketTypeSingle: Int =
        if (selectedTicketType == R.string.screen_store_tickets_reduced) {
            R.string.screen_store_ticket_reduced
        } else {
            R.string.screen_store_ticket_regular
        }
    Text(
        text = title,
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        color = MainColorScheme.surfaceTint,
        textAlign = TextAlign.Left,
        style = Typography.headlineLarge,
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        storeTicketsGroup.forEach { ticket ->
            StoreTicketCard(
                viewModel = viewModel,
                type = ticketTypeSingle,
                ticketId = ticket.id,
                provider = ticket.provider.type,
                scope = ticket.scope.type,
                time = ticket.duration.type,
                unit = ticket.unit.type,
                price = ticket.price.type,
                currency = R.string.currency_pln,
                scopeFormat = R.string.ticket_scope_format,
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun StoreTicketCard(
    viewModel: ListPageViewModel,
    ticketId: Int,
    type: Int,
    provider: String,
    scope: String,
    time: String,
    unit: String,
    price: String,
    currency: Int,
    scopeFormat: Int,
) {
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(4.dp, 2.dp, 4.dp, 2.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(color = MainColorScheme.onPrimary)
            .clickable {
                viewModel.onTicketPicked(ticketId)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .width(160.dp)
                .height(220.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(8.dp),
                )
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = MainColorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(id = type),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (type == R.string.screen_store_ticket_reduced) {
                            MainColorScheme.outline
                        } else {
                            MainColorScheme.onTertiary
                        }
                    )
                    .padding(4.dp),
                color = MainColorScheme.primary,
                textAlign = TextAlign.Center,
                style = Typography.bodySmall,
            )
            Text(
                text = provider,
                modifier = Modifier
                    .padding(8.dp),
                color = MainColorScheme.surface,
                textAlign = TextAlign.Center,
                style = Typography.bodySmall,
            )
            Text(
                text = stringResource(id = scopeFormat, scope),
                modifier = Modifier
                    .padding(8.dp),
                color = MainColorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = Typography.bodySmall,
            )
            Text(
                text = time,
                color = MainColorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = if (unit == UnitDb.GROUP_TRIP.type) {
                    Typography.labelSmall
                } else if (time == DurationDb.WEEKEND.type) {
                    Typography.displayLarge
                } else if (unit == UnitDb.MINUTE_OR_TRIP.type) {
                    Typography.labelMedium
                } else {
                    Typography.labelLarge
                }
            )
            Text(
                text = unit,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 24.dp, 0.dp),
                color = MainColorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            color = MainColorScheme.surfaceTint,
            modifier = Modifier
                .width(160.dp)
                .padding(12.dp),
            textAlign = TextAlign.Left,
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 24.sp)) {
                    append(price)
                }
                append(" ")
                append(stringResource(id = currency))
            },
            style = Typography.titleSmall,
        )
    }
}
