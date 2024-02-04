package com.volodymyr.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.volodymyr.provider.NavigationProvider
import com.volodymyr.ui.theme.MainColorScheme

@Destination(start = true)
@Composable
fun ListPage(
    id: Int = 0,
    navigator: NavigationProvider
) {
    var selectedTabListIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MainColorScheme.surface)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(
            stringResource(id = R.string.screen_tickets_title),
            balance = 4.80,
            unit = stringResource(
                id = R.string.currency
            )
        )
        ListSelector(selectedTabListIndex) { newIndex ->
            selectedTabListIndex = newIndex
        }
        if (selectedTabListIndex == 0) ListStoreTickets() else ListUsersTickets(navigator)
    }
}

@Composable
fun Title(
    title: String,
    balance: Double,
    unit: String,
) {
    val imgProfile = painterResource(R.drawable.profile)
    val balanceFormatted = String.format("%.2f", balance)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MainColorScheme.primary
            )
            .padding(bottom = 12.dp),
    ) {
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            textAlign = TextAlign.Left,
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 18.sp
            )
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd),
        ) {
            Text(
                text = stringResource(
                    id = R.string.price,
                    balanceFormatted,
                    unit
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = MainColorScheme.onTertiary)
                    .padding(6.dp),
                color = Color.White,
                textAlign = TextAlign.Left,
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Image(
                painter = imgProfile,
                contentDescription = stringResource(id = R.string.screen_tickets_image_profile),
                modifier = Modifier
                    .padding(8.dp, 12.dp, 8.dp, 12.dp)
                    .size(20.dp)
                    .background(
                        color = Color.Transparent
                    )
                    .fillMaxSize(),
//                    .clickable { pressOnBack.invoke() }
            )
        }
    }
}

@Composable
fun ListSelector(selectedTabListIndex: Int, onTabSelected: (Int) -> Unit) {
    val items = listOf(
        stringResource(id = R.string.screen_tickets_selector_store),
        stringResource(id = R.string.screen_tickets_selector_users_tickets),
    )
    Row(
        modifier = Modifier
            .background(
                color = MainColorScheme.primary
            )
            .padding(36.dp, 0.dp, 36.dp, 0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items.forEachIndexed { index, item ->
            Text(
                text = AnnotatedString(item),
                modifier = Modifier
                    .drawBehind {

                        val strokeWidth = 5F
                        val y = size.height - strokeWidth

                        drawLine(
                            color = if (selectedTabListIndex == index) MainColorScheme.outline else Color.Transparent,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
                    .padding(8.dp)
                    .clickable {
                        onTabSelected(index)
                    },
                color = if (selectedTabListIndex == index) MainColorScheme.outline else Color.Gray,
                style = TextStyle(
                    fontSize = 18.sp
                )
            )
        }
    }
}

