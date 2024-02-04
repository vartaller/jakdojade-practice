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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.volodymyr.ui.theme.MainColorScheme

@Composable
fun ListStoreTickets() {
    var selectedTypeIndex by remember { mutableStateOf(0) }
    TicketTypeSelector(selectedTypeIndex) { newIndex ->
        selectedTypeIndex = newIndex
    }
    TicketGroup(
        title = stringResource(id = R.string.tickets_group_minutes),
        selectedTypeIndex = selectedTypeIndex
    )
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray)
    )
    TicketGroup(
        title = stringResource(id = R.string.tickets_group_hours),
        selectedTypeIndex = selectedTypeIndex
    )
    Spacer(
        modifier = Modifier
            .height(8.dp)
            .fillMaxWidth()
            .background(color = Color.LightGray)
    )
    TicketGroup(
        title = stringResource(id = R.string.tickets_group_grouped),
        selectedTypeIndex = selectedTypeIndex
    )
}


@Composable
fun TicketTypeSelector(selectedTabIndex: Int, onTabSelected: (Int) -> Unit) {
    val items = listOf(
        stringResource(id = R.string.screen_store_ticket_reduced),
        stringResource(id = R.string.screen_store_ticket_normal),
    )
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(24.dp))
            .background(color = Color.LightGray),
        horizontalArrangement = Arrangement.Center
    ) {
        items.forEachIndexed { index, item ->
            Text(
                text = AnnotatedString(item),
                modifier = Modifier
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(24.dp))
                    .background(color = if (selectedTabIndex == index) Color.White else Color.Transparent)
                    .clickable {
                        onTabSelected(index)
                    }
                    .padding(16.dp, 8.dp, 16.dp, 8.dp),
                color = if (selectedTabIndex == index) Color.DarkGray else Color.Gray,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }
}


@Composable
fun TicketGroup(
    title: String,
    selectedTypeIndex: Int,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(12.dp),
            color = Color.Black,
            textAlign = TextAlign.Left,
            style = TextStyle(
                fontSize = 20.sp
            )
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            TicketCard(
                selectedTypeIndex,
                stringResource(id = R.string.ticket_provider),
                stringResource(id = R.string.ticket_scope_country),
                stringResource(id = R.string.ticket_duration_20),
                stringResource(id = R.string.ticket_unit_minutes),
                stringResource(id = R.string.price_reduced_minutes_lowest),
                stringResource(id = R.string.currency),
            )
            TicketCard(
                selectedTypeIndex,
                stringResource(id = R.string.ticket_provider),
                stringResource(id = R.string.ticket_scope_country),
                stringResource(id = R.string.ticket_duration_60),
                stringResource(id = R.string.ticket_unit_minutes_or_trip),
                stringResource(id = R.string.price_reduced_minutes_middle),
                stringResource(id = R.string.currency),
            )
            TicketCard(
                selectedTypeIndex,
                stringResource(id = R.string.ticket_provider),
                stringResource(id = R.string.ticket_scope_country),
                stringResource(id = R.string.ticket_duration_90),
                stringResource(id = R.string.ticket_unit_minutes),
                stringResource(id = R.string.price_reduced_minutes_highest),
                stringResource(id = R.string.currency),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun TicketCard(
    selectedTypeIndex: Int,
    provider: String,
    scope: String,
    time: String,
    unit: String,
    price: String,
    currency: String
) {
    val ticketType =
        if (selectedTypeIndex == 0) stringResource(
            id = R.string.ticket_type_reduced
        )
        else stringResource(
            id = R.string.ticket_type_normal
        )
    Column(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .padding(4.dp, 2.dp, 4.dp, 2.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .background(color = Color.White),
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
                text = ticketType,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(
                        color = if (selectedTypeIndex == 0) MainColorScheme.outline
                        else Color.LightGray
                    )
                    .padding(4.dp),
                color = MainColorScheme.primary,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
            Text(
                text = provider,
                modifier = Modifier
                    .padding(8.dp),
                color = Color.Gray,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
            Text(
                text = stringResource(id = R.string.ticket_scope_template, scope),
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
            Text(
                text = time,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 80.sp
                )
            )
            Text(
                text = unit,
                modifier = Modifier
                    .padding(24.dp, 0.dp, 24.dp, 0.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(48.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
            Text(
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
                style = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 12.sp
                )
            )
    }
}
