package com.example.cse226

import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cse226.ui.theme.CSE226Theme
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.jar.Manifest

class Activity1 : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimerScreen()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 500, heightDp = 500)
@Composable
fun TimerScreen(){
    var show by remember{ mutableStateOf(false) }
    var time by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Icon(Icons.Default.Face,"")
            Spacer(Modifier.padding(6.dp))
            Text("Current Time",
                style = MaterialTheme.typography.headlineSmall)
        }
        Spacer(Modifier.height(8.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
                LaunchedEffect(show) {
                    if (show) {
                        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss a")
                        while (show) {
                    time = LocalTime.now().format(formatter)
                    kotlinx.coroutines.delay(1000)
                                Log.d("Timer Clock","Current Time: $time")
                }
            }
        }
            Box(modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center) {
                Text(
                    "$time",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Blue
                )
            }

        }
        Spacer(Modifier.height(5.dp))
        val format= DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")
        date= LocalDate.now().format(format)
        Text("$date")
        Spacer(Modifier.height(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = {show=true},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                )
            ) {
                Text(" Start Timer")
            }
            Spacer(Modifier.padding(8.dp))
            Button(
                onClick = {show=false},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(" Stop Timer")
            }
        }
            Spacer(Modifier.height(8.dp))
            Row {
                Text("Timer Status: ")
                Spacer(Modifier.width(10.dp))
                Text(
                    if (show) {

                        "Running"
                    }
                    else {
                        "Not Running"

                    }
                )
                if(show){
                    SideEffect{
                        Log.d("Timer Started","Timer started...")
                    }
                }
                else{
                    SideEffect {
                        Log.d("Timer Stopped","Timer stopped...")
                    }
                }
            }
    }
}
