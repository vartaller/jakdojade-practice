package com.volodymyr.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.volodymyr.data.DurationDb
import com.volodymyr.data.R
import com.volodymyr.data.UnitDb
import com.volodymyr.list.PurchasePageEffect
import com.volodymyr.list.PurchasePageUiState
import com.volodymyr.list.PurchasePageViewModel
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography

@Destination(start = false)
@Composable
fun PurchasePage(
    navigator: NavigationProvider,
    viewModel: PurchasePageViewModel = hiltViewModel(),
    ticketId: Int
) {
    val state by viewModel.uiState.collectAsState()
    val effect by viewModel.effect.collectAsState(initial = null)
    PurchasePageEffectReaction(
        effect = effect, navigator = navigator, viewModel = viewModel
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainColorScheme.tertiary),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            pressOnBack = {
                navigator.navigateUp()
            }, state = state
        )
        state.ticket?.let {
            StoreTicketCard(
                type = it.type.type,
                provider = it.provider.type,
                scope = it.scope.type,
                time = it.duration.type,
                unit = it.unit.type,
                price = it.price.type,
                currency = R.string.currency_pln,
                scopeFormat = R.string.ticket_scope_format,
            )
        }
        QuantityPanel(state = state) { changeTicketAmountTo ->
            viewModel.updateTicketAmount(changeTicketAmountTo = changeTicketAmountTo)
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = com.volodymyr.list.R.string.screen_ticket_purchase_some_text),
            color = MainColorScheme.surface,
            style = Typography.bodySmall,
            textAlign = TextAlign.Center,
        )
        PurchasePanel(pressOnBuy = {
            state.ticket?.let { nonNullTicket ->
                viewModel.insertUserTickets(
                    storeTicket = nonNullTicket, amount = state.ticketAmount
                )
            }
        }, state = state)
    }
}

@Composable
fun PurchasePageEffectReaction(
    effect: PurchasePageEffect?, navigator: NavigationProvider, viewModel: PurchasePageViewModel
) {
    when (effect) {
        is PurchasePageEffect.GoBack -> navigator.navigateUp()
        null -> Unit
    }
    viewModel.clearEffect()
}

@Composable
fun Title(
    pressOnBack: () -> Unit = {}, state: PurchasePageUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val imgClose = painterResource(com.volodymyr.list.R.drawable.close)
        Image(
            painter = imgClose,
            contentDescription = stringResource(id = R.string.close),
            modifier = Modifier
                .padding(0.dp, 4.dp)
                .size(12.dp)
                .background(
                    color = Color.Transparent
                )
                .fillMaxSize()
                .clickable {
                    pressOnBack.invoke()
                },
// ------------------ use to close app by clicking back
//                    .clickable { pressOnBack.invoke() }
        )
        Text(
            text = stringResource(id = R.string.seconds_unit, state.timeCounter),
            color = MainColorScheme.surfaceTint,
            style = Typography.bodyMedium,
        )
    }
}

@Composable
fun PurchasePanel(
    pressOnBuy: () -> Unit = {}, state: PurchasePageUiState
) {
    val ticketPrice: Double = state.ticket?.price?.type?.toDoubleOrNull() ?: 0.0
    val formattedPrice = String.format(
        stringResource(id = R.string.balance_format),
        state.ticketAmount * ticketPrice
    )
    val imgWallet = painterResource(com.volodymyr.list.R.drawable.wallet)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(12.dp))
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(color = MainColorScheme.onPrimary)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(12.dp),
            text = stringResource(id = com.volodymyr.list.R.string.screen_ticket_purchase_some_text),
            color = MainColorScheme.surface,
            style = Typography.bodySmall,
            textAlign = TextAlign.Center,
        )
        Box(
            modifier = Modifier
                .padding(8.dp, 0.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = MainColorScheme.outline)
                .padding(32.dp, 8.dp)
                .height(42.dp), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.clickable {
                    pressOnBuy.invoke()
                },
                text = if (state.ticketAmount > 1) {
                    stringResource(
                        id = R.string.screen_ticket_purchase_buy_full,
                        state.ticketAmount,
                        formattedPrice,
                        stringResource(
                            id = R.string.currency_pln
                        )
                    )
                } else {
                    stringResource(id = R.string.screen_ticket_purchase_buy_short)
                },
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.screen_ticket_purchase_method),
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
            Image(
                painter = imgWallet,
                contentDescription = stringResource(id = R.string.screen_ticket_purchase_wallet),
                modifier = Modifier
                    .padding(0.dp, 4.dp)
                    .size(32.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = R.string.user_balance_hardcoded),
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun QuantityPanel(
    state: PurchasePageUiState, onTicketChanged: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .width(160.dp)
            .background(color = MainColorScheme.onPrimary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .width(34.dp)
                .padding(4.dp)
                .border(
                    width = 1.dp, color = if (state.ticketAmount > 1) {
                        MainColorScheme.surface
                    } else {
                        MainColorScheme.onTertiary
                    }, shape = RoundedCornerShape(50)
                )
                .clickable(enabled = state.ticketAmount > 1) { onTicketChanged(-1) },
            text = "-",
            textAlign = TextAlign.Center,
            color = MainColorScheme.surfaceTint,
            style = Typography.titleLarge,
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = state.ticketAmount.toString(),
            color = MainColorScheme.surfaceTint,
            style = Typography.bodyMedium,
        )
        Text(
            modifier = Modifier
                .width(34.dp)
                .padding(4.dp)
                .border(
                    width = 1.dp, color = MainColorScheme.surface, shape = RoundedCornerShape(50)
                )
                .clickable { onTicketChanged(1) },
            text = "+",
            textAlign = TextAlign.Center,
            color = MainColorScheme.surfaceTint,
            style = Typography.titleLarge,
        )
    }
}

@Composable
fun StoreTicketCard(
    type: String,
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
            .background(color = MainColorScheme.onPrimary),
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
                text = type,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (type == stringResource(id = R.string.screen_store_ticket_reduced)) {
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
                modifier = Modifier.padding(8.dp),
                color = MainColorScheme.surface,
                textAlign = TextAlign.Center,
                style = Typography.bodySmall,
            )
            Text(
                text = stringResource(id = scopeFormat, scope),
                modifier = Modifier.padding(8.dp),
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
                modifier = Modifier.padding(24.dp, 0.dp, 24.dp, 0.dp),
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