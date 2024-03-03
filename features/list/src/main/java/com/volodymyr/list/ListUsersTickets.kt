package com.volodymyr.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.volodymyr.data.DurationDb
import com.volodymyr.data.UnitDb
import com.volodymyr.data.UserTicket
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val columnHeight = 300;
const val columnWidth = 180;

@Composable
fun ListUsersTickets(
    state: ListPageUiState,
    navigator: NavigationProvider,
    viewModel: ListPageViewModel = hiltViewModel(),
) {
    state.allUserTickets.forEach { ticket ->
        UserTicketCard(
            viewModel = viewModel,
            navigator = navigator,
            ticket = ticket,
        )
    }
}

@Composable
fun UserTicketCard(
    ticket: UserTicket,
    viewModel: ListPageViewModel,
    navigator: NavigationProvider,
) {

    val calendar = Calendar.getInstance()
    calendar.time = ticket.dateStamp
    val seconds = calendar.get(Calendar.SECOND).toString().padStart(2, '0')
    val minutes = calendar.get(Calendar.MINUTE).toString().padStart(2, '0')
    val hours = calendar.get(Calendar.HOUR_OF_DAY).toString().padStart(2, '0')
    val day = calendar.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
    val month = (calendar.get(Calendar.MONTH) + 1).toString().padStart(2, '0')
    val year = calendar.get(Calendar.YEAR)
    val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
    val dayName = sdf.format(ticket.dateStamp)

    Spacer(
        modifier = Modifier
            .height(16.dp)
    )
    Text(
        color = MainColorScheme.surfaceTint,
        text = "$year.$month.$day",
        textAlign = TextAlign.Left,
        style = Typography.bodyMedium,
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
        TicketShapeColumn(
            navigator = navigator,
            ticket = ticket,
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
        )
        TicketDataColumn(
            viewModel = viewModel,
            ticket = ticket,
            time = "$hours:$minutes:$seconds",
            day = dayName,
            date = "$year.$month.$day",
        )
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
    ticket: UserTicket,
) {
    Column(
        modifier = Modifier
            .width(columnWidth.dp)
            .height(columnHeight.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(
                color = MainColorScheme.surface
            ),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = ticket.type.type,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp, 8.dp, 4.dp, 4.dp))
                .background(color = MainColorScheme.onTertiary)
                .padding(4.dp),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.bodySmall,
        )
        Text(
            text = ticket.provider.type,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(4.dp),
            color = MainColorScheme.onSurface,
            textAlign = TextAlign.Center,
            style = Typography.bodySmall,
        )
        Text(
            text = stringResource(id = R.string.ticket_scope_format, ticket.scope),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(4.dp),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.bodySmall,
        )
        Text(
            text = ticket.duration.type,
            modifier = Modifier.fillMaxWidth(),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = if (ticket.unit.type == UnitDb.GROUP_TRIP.type) {
                Typography.labelSmall
            } else if (ticket.duration.type == DurationDb.WEEKEND.type) {
                Typography.displayLarge
            } else if (ticket.unit.type == UnitDb.MINUTE_OR_TRIP.type) {
                Typography.labelMedium
            } else {
                Typography.labelLarge
            }
        )
        Text(
            text = ticket.unit.type,
            modifier = Modifier.fillMaxWidth(),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.bodyMedium,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.ticket_submit),
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
                .background(color = MainColorScheme.onPrimary)
                .padding(12.dp)
                .clickable {
                    navigator.navigateToUserTicket(ticketId = ticket.id)
                },
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.bodySmall,
        )
    }
}


@Composable
fun TicketDataColumn(
    viewModel: ListPageViewModel,
    ticket: UserTicket,
    time: String,
    day: String,
    date: String,
) {
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
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
            )
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.calendar),
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
                text = day,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
            )
        }
        Row {
            SpacerImageSize()
            Text(
                text = "$date $time",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
            )
        }
        Row {
            SpacerImageSize()
            Spacer(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = MainColorScheme.onTertiary)
            )
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.price),
                contentDescription = stringResource(id = R.string.ticket_price),
                modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .size(16.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
            )
            Text(
                text = stringResource(
                    id = R.string.price_format,
                    ticket.price.type,
                    stringResource(id = R.string.currency_pln)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
            )
        }
        Row {
            SpacerImageSize()
            Text(
                text = stringResource(id = R.string.ticket_buy_again),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = MainColorScheme.onTertiary)
                    .padding(8.dp)
                    .clickable {
                        println("ticket.storeTicketId = ${ticket.storeTicketId}")
                        ticket.storeTicketId?.let {
                            viewModel.onTicketPicked(ticket.storeTicketId!!)
                        }
                    },
                color = MainColorScheme.surfaceTint,
                textAlign = TextAlign.Center,
                style = Typography.headlineSmall,
            )
        }
        Row {
            SpacerImageSize()
            Spacer(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = MainColorScheme.onTertiary)
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
                text = stringResource(id = R.string.ticket_see_more),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = MainColorScheme.surfaceTint,
                textAlign = TextAlign.Center,
                style = Typography.headlineMedium,
            )
        }
    }
}

//fun manipulateDbButtons(){
//    Text(
//        text = "drop user tickets table",
//        modifier = Modifier
//            .padding(4.dp)
//            .fillMaxWidth()
//            .clip(shape = RoundedCornerShape(8.dp))
//            .background(color = MainColorScheme.onTertiary)
//            .padding(8.dp)
//            .clickable {
//                viewModel.dropTableUserTickets()
//            },
//        color = MainColorScheme.surfaceTint,
//        textAlign = TextAlign.Center,
//        style = Typography.headlineSmall,
//    )
//    Text(
//        text = "populate store tickets table",
//        modifier = Modifier
//            .padding(4.dp)
//            .fillMaxWidth()
//            .clip(shape = RoundedCornerShape(8.dp))
//            .background(color = MainColorScheme.onTertiary)
//            .padding(8.dp)
//            .clickable {
//                viewModel.populateTableStoreTicket()
//            },
//        color = MainColorScheme.surfaceTint,
//        textAlign = TextAlign.Center,
//        style = Typography.headlineSmall,
//    )
//}