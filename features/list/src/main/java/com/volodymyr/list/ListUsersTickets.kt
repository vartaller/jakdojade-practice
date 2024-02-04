package com.volodymyr.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.volodymyr.provider.NavigationProvider

val date = listOf("wtorek", "16.01.2024", "11:45");
val columnHeight = 300;
val columnWidth = 180;

@Composable
fun ListUsersTickets(
    navigator: NavigationProvider
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TicketCard(
            navigator,
            stringResource(id = R.string.ticket_type_reduced),
            stringResource(id = R.string.ticket_provider),
            stringResource(id = R.string.ticket_scope_country),
            stringResource(id = R.string.ticket_duration_60),
            stringResource(id = R.string.ticket_unit_minutes_or_trip),
            stringResource(id = R.string.price_reduced_minutes_middle),
            stringResource(id = R.string.currency),
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
        TicketCard(
            navigator,
            stringResource(id = R.string.ticket_type_reduced),
            stringResource(id = R.string.ticket_provider),
            stringResource(id = R.string.ticket_scope_country),
            stringResource(id = R.string.ticket_duration_60),
            stringResource(id = R.string.ticket_unit_minutes_or_trip),
            stringResource(id = R.string.price_reduced_minutes_middle),
            stringResource(id = R.string.currency),
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
        TicketCard(
            navigator,
            stringResource(id = R.string.ticket_type_reduced),
            stringResource(id = R.string.ticket_provider),
            stringResource(id = R.string.ticket_scope_country),
            stringResource(id = R.string.ticket_duration_60),
            stringResource(id = R.string.ticket_unit_minutes_or_trip),
            stringResource(id = R.string.price_reduced_minutes_middle),
            stringResource(id = R.string.currency),
        )
        Spacer(
            modifier = Modifier
                .height(8.dp)
                .fillMaxWidth()
                .background(color = Color.LightGray)
        )
        TicketCard(
            navigator,
            stringResource(id = R.string.ticket_type_reduced),
            stringResource(id = R.string.ticket_provider),
            stringResource(id = R.string.ticket_scope_country),
            stringResource(id = R.string.ticket_duration_60),
            stringResource(id = R.string.ticket_unit_minutes_or_trip),
            stringResource(id = R.string.price_reduced_minutes_middle),
            stringResource(id = R.string.currency),
        )
    }
}

@Composable
fun TicketCard(
    navigator: NavigationProvider,
    type: String,
    provider: String,
    scope: String,
    time: String,
    unit: String,
    price: String,
    currency: String
) {
    Spacer(
        modifier = Modifier
            .height(16.dp)
    )
    Text(
        text = date[1],
        textAlign = TextAlign.Left,
        style = TextStyle(
            fontSize = 16.sp
        ),
        modifier = Modifier
            .padding(16.dp, 0.dp, 16.dp, 0.dp)
            .fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TicketShapeColumn(navigator, type, provider, scope, time, unit)
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
        )
        TicketDataColumn(price, currency)
    }
}

@Composable
fun SpacerImageSize() {
    Spacer(
        modifier = Modifier
            .width(30.dp)
    )
}

@Composable
fun TicketShapeColumn(
    navigator: NavigationProvider,
    type: String,
    provider: String,
    scope: String,
    time: String,
    unit: String
) {
    Column(
        modifier = Modifier
            .width(columnWidth.dp)
            .height(columnHeight.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(
                color = Color.Gray
            ),
    ) {
        Text(
            text = type,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp, 8.dp, 4.dp, 4.dp))
                .background(color = Color.LightGray)
                .padding(4.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        Text(
            text = provider,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(4.dp),
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        Text(
            text = scope,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(4.dp),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        Text(
            text = time,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.W500,
                fontSize = 80.sp
            )
        )
        Text(
            text = unit,
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp
            )
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = stringResource(id = R.string.ticket_submit),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White)
                    .padding(12.dp)
                    .clickable {
                        navigator.navigateToTicket(resId = 0)
                    },
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }
    }
}


@Composable
fun TicketDataColumn( price: String, currency: String) {
    val imgCalendar = painterResource(R.drawable.calendar)
    val imgPrice = painterResource(R.drawable.price)
    Column(
        modifier = Modifier
            .height(columnHeight.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Row {
            SpacerImageSize()
            Text(
                text = stringResource(id = R.string.ticket_field_from),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        Row {
            Image(
                painter = imgCalendar,
                contentDescription = stringResource(id = R.string.ticket_date),
                modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .size(16.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
            )
            Text(
                text = date[0],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        Row {
            SpacerImageSize()
            Text(
                text = date[1] + " " + date[2],
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        Row {
            SpacerImageSize()
            Spacer(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = Color.LightGray)
            )
        }
        Row {
            Image(
                painter = imgPrice,
                contentDescription = stringResource(id = R.string.ticket_date),
                modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .size(16.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
            )
            Text(
                text = stringResource(R.string.price, price, currency),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.Black,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }
        Row {
            SpacerImageSize()
            Text(
                text = stringResource(R.string.ticket_buy_again, price, currency),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.LightGray)
                    .padding(8.dp),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            )
        }
        Row {
            SpacerImageSize()
            Spacer(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = Color.LightGray)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Row {
            SpacerImageSize()
            Text(
                text = stringResource(R.string.ticket_see_more),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    textDecoration = TextDecoration.Underline,
                    fontSize = 16.sp
                )
            )
        }
    }
}