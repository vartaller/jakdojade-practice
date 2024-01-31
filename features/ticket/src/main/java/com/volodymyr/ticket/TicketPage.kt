package com.volodymyr.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.annotation.Destination
import com.volodymyr.ui.theme.MainColorScheme

@Destination(start = true)
@Composable
fun ListPage(
    id: Int = 0,
//    navigator: NavigationProvider
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = Color.White
            )
    ) {
        Column(
            modifier = Modifier
                .background(
                    color = MainColorScheme.primary
                )
                .padding(12.dp)
        ) {
//            Title(
//                stringResource(id = R.string.screen_ticket_title)
//            )
//            TicketType(
//                stringResource(id = R.string.ticket_type_normal),
//                stringResource(id = R.string.ticket_duration_20),
//                stringResource(id = R.string.ticket_unit_minutes),
//                stringResource(id = R.string.ticket_scope_city),
//            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Spacer(modifier = Modifier.height(12.dp)) }
            item {
                QrCode(false)
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
            items(7) { index ->
//                InfoField(
//                    infoFieldsData[index].title, infoFieldsData[index].value
//                )
            }
        }
    }
}
@Composable
fun Title(title: String) {
    val navController = rememberNavController()

//    NavHost(navController = navController, startDestination = "profile") {
//        composable("myTicket") { MainActivity }
////        composable("friendslist") { FriendsList( /* ... */ ) }
//        // Add more destinations similarly.
//    }

//    val imgArrowBack = painterResource(R.drawable.arrow_back)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
    ) {
//        Image(
//            painter = imgArrowBack,
//            contentDescription = stringResource(id = R.string.screen_ticket_image_arrow_back),
//            modifier = Modifier
//                .size(20.dp)
//                .align(Alignment.CenterStart)
//                .background(
//                    color = Color.Transparent
//                )
//                .fillMaxSize()
//        )
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            color = Color.White,
            textAlign = TextAlign.Left,
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 20.sp
            )
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
                .background(color = Color.LightGray)
                .padding(4.dp),
            color = MainColorScheme.primary,
            style = TextStyle(
                fontSize = 8.sp, letterSpacing = 2.sp
            )
        )
//        Text(
//            text = stringResource(id = R.string.ticket_domain, time, unit, scope),
//            modifier = Modifier.padding(top = 12.dp),
//            color = Color.White,
//            style = TextStyle(
//                fontSize = 28.sp, fontWeight = FontWeight.Bold
//            )
//        )
    }
}

@Composable
fun QrCode(isActive: Boolean) {
//    val imgQr = painterResource(R.drawable.qr_code)

    Column(
        modifier = Modifier
            .width(250.dp)
            .shadow(
                elevation = 16.dp,
                shape = RoundedCornerShape(8.dp),
            )
            .clip(shape = RoundedCornerShape(16.dp))
            .background(
                color = if (isActive) MainColorScheme.tertiary else MainColorScheme.error
            )
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
//        Text(
//            text = if (isActive) {
//                stringResource(id = R.string.ticket_status_active)
//            } else {
//                stringResource(id = R.string.ticket_status_inactive)
//            }, modifier = Modifier.padding(4.dp), color = Color.White, style = TextStyle(
//                fontSize = 16.sp, fontWeight = FontWeight.Bold
//            )
//        )
//        Image(
//            painter = imgQr,
//            contentDescription = stringResource(id = R.string.screen_ticket_image_qr),
//            modifier = Modifier
//                .size(250.dp, 250.dp)
//                .clip(shape = RoundedCornerShape(16.dp))
//                .background(
//                    color = Color.White
//                )
//                .padding(28.dp)
//                .fillMaxSize()
//        )
    }
//    Text(
//        text = stringResource(id = R.string.screen_ticket_qr_scale),
//        modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
//        color = Color.Gray,
//        style = TextStyle(
//            fontSize = 14.sp,
//        )
//    )
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
            text = title, color = Color.Gray, style = TextStyle(
                fontSize = 12.sp,
            )
        )
        Text(
            text = info, modifier = Modifier, style = TextStyle(
                fontSize = 18.sp,
            )
        )
    }
}