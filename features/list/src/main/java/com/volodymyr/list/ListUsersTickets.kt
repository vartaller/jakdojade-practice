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
import com.volodymyr.data.TicketType
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography

const val columnHeight = 300;
const val columnWidth = 180;

@Composable
fun ListUsersTickets(
    state: ListPageUiState,
    navigator: NavigationProvider
) {
    state.userTickets.forEach { ticket ->
        UserTicketCard(
            state = state,
            navigator = navigator,
            type = ticket.ticketType,
            provider = ticket.provider,
            scope = ticket.scope,
            time = ticket.time,
            unit = ticket.unit,
            price = ticket.price,
            currency = ticket.currency,
            scopeFormat = ticket.scopeFormat,
            date = ticket.date,
        )
    }
}

@Composable
fun UserTicketCard(
    state: ListPageUiState,
    navigator: NavigationProvider,
    type: TicketType,
    provider: Int,
    scope: Int,
    time: Int,
    unit: Int,
    price: Int,
    currency: Int,
    scopeFormat: Int,
    date: Date,
) {
    Spacer(
        modifier = Modifier
            .height(16.dp)
    )
    Text(
        color = MainColorScheme.surfaceTint,
        text = date.date,
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
            provider = stringResource(id = provider),
            scope = stringResource(id = scopeFormat, stringResource(id = scope)),
            time = stringResource(id = time),
            unit = stringResource(id = unit),
            ticketSubmitText = stringResource(id = state.ticketSubmitText.textId),
        )
        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .width(8.dp)
        )
        TicketDataColumn(
            date = date,
            imgCalendar = painterResource(id = state.imgCalendar.imgId),
            imgPrice = painterResource(state.imgPrice.imgId),
            textFrom = stringResource(id = state.ticketFromText.textId),
            textBuyAgain = stringResource(id = state.ticketByAgainText.textId),
            textDate = stringResource(id = state.ticketDateText.textId),
            textSeeMore = stringResource(id = state.ticketSeeMoreText.textId),
            priceFormatted = stringResource(
                id = state.priceFormat.formatId,
                stringResource(id = price),
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
    type: TicketType,
    provider: String,
    scope: String,
    time: String,
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
            text = stringResource(id = type.typeId),
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
            text = time,
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
    date: Date,
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
                text = date.day,
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
                text = date.date + " " + date.time,
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