package com.volodymyr.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography


@Destination(start = false)
@Composable
fun TicketPage(
    navigator: NavigationProvider,
    viewModel: TicketPageViewModel = hiltViewModel(),
    ticketId: Int
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MainColorScheme.onPrimary
            )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MainColorScheme.primary
                )
                .padding(12.dp)
        ) {
            Title(
                img = painterResource(id = R.drawable.arrow_back),
                title = stringResource(id = R.string.screen_ticket_title),
                textBack = stringResource(id = R.string.screen_ticket_image_arrow_back),
                pressOnBack = {
                    navigator.navigateUp()
                },
            )
            TicketType(
                type = stringResource(id = state.ticketType.typeId),
                time = state.ticketTime,
                unit = state.ticketTimeUnit,
                scope = state.ticketScope,
                format = state.textScopeFormat.textId
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                QrCode(
                    isActive = false,
                    textTicketActive = stringResource(id = R.string.ticket_status_active),
                    textTicketInactive = stringResource(id = R.string.ticket_status_inactive),
                    textQrDescription = stringResource(id = R.string.screen_ticket_image_qr),
                    textQrScale = stringResource(id = R.string.screen_ticket_qr_scale),
                )
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            items(state.ticketValues.size) { index ->
                InfoField(model = state.ticketValues[index])
            }
        }
    }
}

@Composable
fun Title(
    img: Painter,
    title: String,
    textBack: String,
    pressOnBack: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
    ) {
        Image(
            painter = img,
            contentDescription = textBack,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterStart)
                .background(
                    color = Color.Transparent
                )
                .fillMaxSize()
                .clickable { pressOnBack.invoke() }
        )
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            color = MainColorScheme.onPrimary,
            textAlign = TextAlign.Left,
            style = Typography.titleMedium,
        )
    }
}

@Composable
fun TicketType(
    type: String, time: String, unit: String, scope: String, format: Int
) {
    Column(
        modifier = Modifier.padding(0.dp, 12.dp, 12.dp, 12.dp),
    ) {
        Text(
            text = type,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = MainColorScheme.onTertiary)
                .padding(4.dp),
            color = MainColorScheme.primary,
            style = Typography.displaySmall,
        )
        Text(
            text = stringResource(id = format, time, unit, scope),
            modifier = Modifier.padding(top = 12.dp),
            color = MainColorScheme.onPrimary,
            style = Typography.displayMedium,
        )
    }
}

@Composable
fun QrCode(
    isActive: Boolean,
    textTicketActive: String,
    textTicketInactive: String,
    textQrDescription: String,
    textQrScale: String,
) {
    val imgQr = painterResource(R.drawable.qr_code)

    Column(
        modifier = Modifier
            .width(250.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .background(
                color = if (isActive) {
                    MainColorScheme.tertiary
                } else {
                    MainColorScheme.error
                }
            )
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = if (isActive) {
                textTicketActive
            } else {
                textTicketInactive
            },
            modifier = Modifier.padding(4.dp),
            color = MainColorScheme.onPrimary,
            style = Typography.titleMedium,
        )
        Image(
            painter = imgQr,
            contentDescription = textQrDescription,
            modifier = Modifier
                .size(250.dp, 250.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    color = MainColorScheme.onPrimary
                )
                .padding(28.dp)
                .fillMaxSize()
        )
    }
    Text(
        text = textQrScale,
        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
        color = MainColorScheme.surface,
        style = Typography.bodyMedium,
    )
}

@Composable
fun InfoField(model: TicketDataModel) {
    Column(
        modifier = Modifier
            .padding(bottom = 12.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                color = MainColorScheme.tertiary
            )
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = model.title.nameId),
            color = MainColorScheme.surface,
            style = Typography.bodySmall,
        )
        Text(
            text = model.value,
            modifier = Modifier,
            color = MainColorScheme.surfaceTint,
            style = Typography.bodyLarge,
        )
    }
}