package com.volodymyr.ticket

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme
import com.volodymyr.ui.theme.Typography

data class InfoFieldData(val title: String, val value: String)

val infoFieldsData = listOf(
    InfoFieldData("Wazny od", "16.01.2024 11:45:47"),
    InfoFieldData("Wazny do", "16.01.2024 12:05:47"),
    InfoFieldData("Numer telefonu", "49 765765765"),
    InfoFieldData("Numer boczny pojazdu", "HL417"),
    InfoFieldData("Cena", "4,00 zl"),
    InfoFieldData("Wystawca biletu", "ZTP w Krakowie"),
    InfoFieldData("Kod biletu", "33A52GQ9")
)

@Destination(start = true)
@Composable
fun TicketPage(
    id: Int = 0,
    navigator: NavigationProvider
) {
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
                stringResource(id = R.string.screen_ticket_title),
                pressOnBack = {
                    navigator.navigateUp()
                },
            )
            TicketType(
                stringResource(id = R.string.ticket_type_normal),
                stringResource(id = R.string.ticket_duration_20),
                stringResource(id = R.string.ticket_unit_minutes),
                stringResource(id = R.string.ticket_scope_city),
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
                QrCode(isActive = false)
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            items(7) { index ->
                InfoField(
                    title = infoFieldsData[index].title,
                    info = infoFieldsData[index].value
                )
            }
        }
    }
}

@Composable
fun Title(
    title: String,
    pressOnBack: () -> Unit = {},
) {

    val imgArrowBack = painterResource(R.drawable.arrow_back)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
    ) {
        Image(
            painter = imgArrowBack,
            contentDescription = stringResource(id = R.string.screen_ticket_image_arrow_back),
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
    type: String, time: String, unit: String, scope: String
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
            text = stringResource(id = R.string.ticket_domain, time, unit, scope),
            modifier = Modifier.padding(top = 12.dp),
            color = MainColorScheme.onPrimary,
            style = Typography.displayMedium,
        )
    }
}

@Composable
fun QrCode(isActive: Boolean) {
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
                stringResource(id = R.string.ticket_status_active)
            } else {
                stringResource(id = R.string.ticket_status_inactive)
            },
            modifier = Modifier.padding(4.dp),
            color = MainColorScheme.onPrimary,
            style = Typography.titleMedium,
        )
        Image(
            painter = imgQr,
            contentDescription = stringResource(id = R.string.screen_ticket_image_qr),
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
        text = stringResource(id = R.string.screen_ticket_qr_scale),
        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
        color = MainColorScheme.surface,
        style = Typography.bodyMedium,
    )
}

@Composable
fun InfoField(title: String, info: String) {
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
            text = title,
            color = MainColorScheme.surface,
            style = Typography.bodySmall,
        )
        Text(
            text = info,
            modifier = Modifier,
            color = MainColorScheme.surfaceTint,
            style = Typography.bodyLarge,
        )
    }
}