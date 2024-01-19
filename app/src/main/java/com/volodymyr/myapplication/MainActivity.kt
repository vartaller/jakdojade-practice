package com.volodymyr.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.volodymyr.myapplication.ui.theme.MyApplicationTheme

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = getColor(R.color.violet)
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.background(
                            color = Color.White
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentHeight(),
                        ) {
                            Column(
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFF282542)
                                    )
                                    .padding(12.dp)
                            ) {
                                Title("Ticket control")
                                TicketType(
                                    "NORMALNY", "20", "minut", "I+II+III"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 36.dp),
                                contentAlignment = Alignment.TopCenter
                            ) {
                                LazyColumn(
                                    modifier = Modifier.padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    item {
                                        QrCode(false, "gfd")
                                    }
                                    items(7) { index ->
                                        InfoField(
                                            infoFieldsData[index].title,
                                            infoFieldsData[index].value
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 12.dp),
    ) {
        Text(
            text = "\u2190",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(end = 12.dp),
            color = Color.White,
            style = TextStyle(
                fontWeight = FontWeight.Bold, fontSize = 18.sp
            )
        )
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
    type: String, time: String, unit: String, scope: String, modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(0.dp, 12.dp, 12.dp, 12.dp),
    ) {
        Box(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(4.dp))
                .background(color = Color.LightGray)
        ) {
            Text(
                text = type,
                modifier = modifier.padding(4.dp),
                color = Color(0xFF282542),
                style = TextStyle(
                    fontSize = 8.sp,
                    letterSpacing = 2.sp
                )
            )
        }
        Text(
            text = "$time $unit, Strefa: $scope",
            modifier = modifier.padding(top = 12.dp),
            color = Color.White,
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

@Composable

fun QrCode(isActive: Boolean, codePath: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.qr_code)

    Column(
        modifier = Modifier
            .width(250.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(
                color = if (isActive) Color(0xFF81C784) else Color(0xFFE57373)
            )
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = if (isActive) "Активен" else "Недействителен",
            modifier = modifier.padding(4.dp),
            color = Color.White,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Box(
            modifier = Modifier
                .size(250.dp, 250.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    color = Color.White
                )
//                .shadow(
//                    elevation = 8.dp,
//                    shape = RoundedCornerShape(8.dp),
//                    clip = false
//                )
                .border(2.dp, Color.LightGray, RoundedCornerShape(16.dp))
                .padding(28.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = image,
                contentDescription = "QR code",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
    Text(
        text = "Нажми, чтобы увеличить",
        modifier = modifier.padding(top = 4.dp, bottom = 16.dp),
        color = Color.Gray,
        style = TextStyle(
            fontSize = 14.sp,
        )
    )
}

@Composable

fun InfoField(title: String, info: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(
                    color = Color(0xFFEDEDED)
                )
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.padding(4.dp, 4.dp, 4.dp, 0.dp),
                color = Color.Gray,
                style = TextStyle(
                    fontSize = 12.sp,
                )
            )
            Text(
                text = info,
                modifier = Modifier.padding(4.dp, 0.dp, 4.dp, 4.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                )
            )
        }
    }
}

@Composable
fun TicketData() {

}

