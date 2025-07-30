package com.sanju.jetpackclass

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sanju.jetpackclass.QuoteApp.model.QuoteList
import com.sanju.jetpackclass.ui.theme.JetpackClassTheme
import com.sanju.jetpackclass.ui.theme.JetpackTheme

class DemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Don't draw under the system bars (so content starts **below** status bar)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = true // true if your status bar background is light

            // Set system bar colors
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color(0xFF2196F3), // Use any color you like
                    darkIcons = true
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0xFF020202),
                    darkIcons = true
                )
            }

            JetpackTheme {
                LaunchedEffect(Unit) {
                    systemUiController.setStatusBarColor(Color(0xFF2196F3), darkIcons = true)
                }
                Column() {
                    Text(
                        text = "Demo App",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(8.dp, 24.dp)
                            .fillMaxWidth(1f),
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    )

                    changeTheme()

                    ListComposable()

                    NotificationScreen()
                }
            }
        }
    }
}

//sideeffect---------------
var counter = 1
@Composable
fun SideEffect() {
    counter++
    Text("Hello Sanju")
}

@Composable
fun ListComposable(){
    val categoryState = remember{ mutableStateOf(emptyList<String>()) }
    categoryState.value = fetchCategories()

    LazyColumn {
        items(categoryState.value){ item ->
            Text(text = item)
        }
    }
}

fun fetchCategories(): List<String> {
    return listOf("one", "two", "three")
}

@Composable
fun changeTheme() {
    var theme = remember { mutableStateOf(false) }
    JetpackTheme(theme.value) {
        Column(Modifier.background(MaterialTheme.colorScheme.background)) {
            Text(
                text = "Theme Changing Button",
//        textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp, 24.dp)
                    .fillMaxWidth(1f),
                style = MaterialTheme.typography.bodySmall,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            )

            Button(onClick = {
                theme.value = !theme.value
            }) {
                Text(text = "Change Theme")
            }
        }
    }
}

@Composable
fun Demo(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "a")
@Composable
fun Preview() {
    JetpackTheme {
//        Greeting("First")
    }
}

@Preview(showBackground = true, name = "b", showSystemUi = true)
@Composable
fun PreviewText(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Jetpack!",
        modifier = modifier,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.ExtraBold,
        color = Color.Red,
        fontSize = 36.sp,
        textAlign = TextAlign.Center,
    )
}

@Preview(showBackground = true, name = "c", showSystemUi = true)
@Composable
fun PreviewColumn(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Hello Jetpack!")
        Text(text = "Hi Hello!")
    }
}

@Preview(showBackground = true, name = "g", showSystemUi = true)
@Composable
fun PreviewRow(modifier: Modifier = Modifier) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = "Hello Jetpack!")
        Text(text = "Hi Hello!")
    }
}

@Preview(showBackground = true, name = "h", showSystemUi = true)
@Composable
fun PreviewBox(modifier: Modifier = Modifier) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.heart_broken),
            contentDescription = ""
        )
        Image(
            painter = painterResource(id = R.drawable.twotone_360),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true, name = "d", widthDp = 400, heightDp = 500)
@Composable
fun PreviewImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.heart_broken),
        contentDescription = "Image",
        colorFilter = ColorFilter.tint(Color.Blue),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true, name = "e", widthDp = 400, heightDp = 500)
@Composable
fun PreviewButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {}, colors = ButtonDefaults.buttonColors(
            contentColor = Color.White
        ), enabled = false
    ) {
        Text(text = "Button")
        Image(
            painter = painterResource(id = R.drawable.heart_broken),
            contentDescription = "Dummy"
        )
    }
}

@Preview(showBackground = true, name = "f", widthDp = 400, heightDp = 500)
@Composable
fun PreviewTextInput(modifier: Modifier = Modifier) {
    val state = remember { mutableStateOf("") }
    TextField(
        value = state.value,
        onValueChange = {
            state.value = it
        },
        label = { Text(text = "Enter Message") })
}

@Preview
@Composable
fun NotificationScreen(){
    var count: MutableState<Int> = rememberSaveable{ mutableStateOf(0) }

    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(WindowInsets.systemBars.asPaddingValues()), // Keeps below status bar
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        NotificationCenter(count.value) { count.value++ }
        MessageBar(count.value)
    }
}

@Composable
fun MessageBar(count: Int) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Image(imageVector = Icons.Outlined.Favorite,
                contentDescription = "",
                Modifier.padding(4.dp))
            Text("Message sent so far - $count")
        }
    }
}

@Composable
fun NotificationCenter(count: Int, increment: () -> Unit) {
//    var count: MutableState<Int> = remember { mutableStateOf(0) }
    Column (verticalArrangement = Arrangement.Center){
        Text("You have sent ${count} notifications")
        Button(onClick = {
            increment()
            Log.d("sanju", "NotificationCenter: "+ count)
        }) {
            Text("Sent Notification")
        }
    }
}
