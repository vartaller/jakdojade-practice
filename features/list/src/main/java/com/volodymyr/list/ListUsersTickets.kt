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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.volodymyr.data.DurationDb
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

const val columnHeight = 300;
const val columnWidth = 180;

@Composable
fun ListUsersTickets(
    state: ListPageUiState,
    navigator: NavigationProvider,
    viewModel: ListPageViewModel = hiltViewModel(),
) {
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
    state.allUserTickets.forEach { ticket ->
        UserTicketCard(
            state = state,
            navigator = navigator,
            type = ticket.type.type,
            provider = ticket.provider.type,
            scope = ticket.scope.type,
            unit = ticket.unit.type,
            price = ticket.price.type,
            currency = R.string.currency_pln,
            scopeFormat = R.string.ticket_scope_format,
            duration = ticket.duration,
            dateStamp = ticket.dateStamp,
        )
    }
}

@Composable
fun UserTicketCard(
    state: ListPageUiState,
    navigator: NavigationProvider,
    type: String,
    provider: String,
    scope: String,
    unit: String,
    price: String,
    currency: Int,
    scopeFormat: Int,
    duration: DurationDb,
    dateStamp: Date,
) {

    val calendar = Calendar.getInstance()
    calendar.time = dateStamp
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)
    val seconds = calendar.get(Calendar.SECOND)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
    val dayName = sdf.format(dateStamp)

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
            type = type,
            provider = provider,
            scope = stringResource(id = scopeFormat, scope),
            duration = duration.type,
            unit = unit,
            ticketSubmitText = stringResource(id = R.string.ticket_submit),
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
        )
        TicketDataColumn(
            time = "$hours:$minutes:$seconds",
            day = dayName,
            date = "$year.$month.$day",
            imgCalendar = painterResource(id = R.drawable.calendar),
            imgPrice = painterResource(id = R.drawable.price),
            textFrom = stringResource(id = R.string.ticket_field_from),
            textBuyAgain = stringResource(id = R.string.ticket_buy_again),
            textDate = stringResource(id = R.string.ticket_date),
            textSeeMore = stringResource(id = R.string.ticket_see_more),
            priceFormatted = stringResource(
                id = R.string.price_format,
                price,
                stringResource(id = currency)
            ),
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
    type: String,
    provider: String,
    scope: String,
    duration: String,
    unit: String,
    ticketSubmitText: String,
) {
    Column(
        modifier = Modifier
            .width(columnWidth.dp)
            .height(columnHeight.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(
                color = MainColorScheme.surface
            ),
    ) {
        Text(
            text = type,
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
            text = provider,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(4.dp),
            color = MainColorScheme.surface,
            textAlign = TextAlign.Center,
            style = Typography.bodySmall,
        )
        Text(
            text = scope,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .padding(4.dp),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.bodySmall,
        )
        Text(
            text = duration,
            modifier = Modifier.fillMaxWidth(),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.labelLarge,
        )
        Text(
            text = unit,
            modifier = Modifier.fillMaxWidth(),
            color = MainColorScheme.surfaceTint,
            textAlign = TextAlign.Center,
            style = Typography.bodyMedium,
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                text = ticketSubmitText,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = MainColorScheme.onPrimary)
                    .padding(12.dp)
                    .clickable {
                        navigator.navigateToTicket(resId = 0)
                    },
                color = MainColorScheme.surfaceTint,
                textAlign = TextAlign.Center,
                style = Typography.bodySmall,
            )
        }
    }
}


@Composable
fun TicketDataColumn(
    time: String,
    day: String,
    date: String,
    imgCalendar: Painter,
    imgPrice: Painter,
    textFrom: String,
    textDate: String,
    textBuyAgain: String,
    textSeeMore: String,
    priceFormatted: String,
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
                text = textFrom,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                color = MainColorScheme.surfaceTint,
                style = Typography.bodyMedium,
            )
        }
        Row {
            Image(
                painter = imgCalendar,
                contentDescription = textDate,
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
                painter = imgPrice,
                contentDescription = textDate,
                modifier = Modifier
                    .padding(8.dp, 4.dp, 8.dp, 4.dp)
                    .size(16.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
            )
            Text(
                text = priceFormatted,
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
                text = textBuyAgain,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = MainColorScheme.onTertiary)
                    .padding(8.dp),
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
                text = textSeeMore,
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