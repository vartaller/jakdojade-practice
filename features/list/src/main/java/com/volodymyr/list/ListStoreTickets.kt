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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography

@Composable
fun ListStoreTickets() {

    val REDUCED_TICKET = stringResource(id = R.string.screen_store_ticket_reduced)
    val NORMAL_TICKET = stringResource(id = R.string.screen_store_ticket_normal)
    var selectedTicketType by remember { mutableStateOf(REDUCED_TICKET) }
    TicketTypeSelector(selectedTicketType = selectedTicketType) { newSelectedTicketType ->
        selectedTicketType = newSelectedTicketType
    }
    TicketGroup(
        title = stringResource(id = R.string.tickets_group_minutes),
        selectedTicketType = selectedTicketType
    )
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(color = MainColorScheme.onTertiary)
    )
    TicketGroup(
        title = stringResource(id = R.string.tickets_group_hours),
        selectedTicketType = selectedTicketType
    )
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(color = MainColorScheme.onTertiary)
    )
    TicketGroup(
        title = stringResource(id = R.string.tickets_group_grouped),
        selectedTicketType = selectedTicketType
    )
}


@Composable
fun TicketTypeSelector(selectedTicketType: String, onTabSelected: (String) -> Unit) {
    val items = listOf(
        stringResource(id = R.string.screen_store_ticket_reduced),
        stringResource(id = R.string.screen_store_ticket_normal),
    )
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(24.dp))
            .background(color = MainColorScheme.onTertiary),
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            Text(
                text = item,
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(
                        color = if (selectedTicketType == item) {
                            MainColorScheme.onPrimary
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable {
                        onTabSelected(item)
                    }
                    .padding(16.dp, 8.dp, 16.dp, 8.dp),
                color = if (selectedTicketType == item) {
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
    title: String,
    selectedTicketType: String,
) {
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
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        TicketCard(
            selectedTicketType = selectedTicketType,
            provider = stringResource(id = R.string.ticket_provider),
            scope = stringResource(id = R.string.ticket_scope_country),
            time = stringResource(id = R.string.ticket_duration_20),
            unit = stringResource(id = R.string.ticket_unit_minutes),
            price = stringResource(id = R.string.price_reduced_minutes_lowest),
            currency = stringResource(id = R.string.currency),
        )
        TicketCard(
            selectedTicketType = selectedTicketType,
            provider = stringResource(id = R.string.ticket_provider),
            scope = stringResource(id = R.string.ticket_scope_country),
            time = stringResource(id = R.string.ticket_duration_60),
            unit = stringResource(id = R.string.ticket_unit_minutes_or_trip),
            price = stringResource(id = R.string.price_reduced_minutes_middle),
            currency = stringResource(id = R.string.currency),
        )
        TicketCard(
            selectedTicketType = selectedTicketType,
            provider = stringResource(id = R.string.ticket_provider),
            scope = stringResource(id = R.string.ticket_scope_country),
            time = stringResource(id = R.string.ticket_duration_90),
            unit = stringResource(id = R.string.ticket_unit_minutes),
            price = stringResource(id = R.string.price_reduced_minutes_highest),
            currency = stringResource(id = R.string.currency),
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun TicketCard(
    selectedTicketType: String,
    provider: String,
    scope: String,
    time: String,
    unit: String,
    price: String,
    currency: String
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = selectedTicketType,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (selectedTicketType == stringResource(id = R.string.screen_store_ticket_reduced)) {
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
                text = stringResource(id = R.string.ticket_scope_template, scope),
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
                style = Typography.labelLarge
            )
            Text(
                text = unit,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 24.dp, 0.dp),
                color = MainColorScheme.onPrimary,
                textAlign = TextAlign.Center,
                style = Typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(48.dp))
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
                append(currency)
            },
            style = Typography.labelSmall,
        )
    }
}
