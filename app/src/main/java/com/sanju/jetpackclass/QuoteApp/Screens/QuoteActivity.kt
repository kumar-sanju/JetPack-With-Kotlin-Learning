package com.sanju.jetpackclass.QuoteApp.Screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)

        CoroutineScope(Dispatchers.IO).launch {
            DataManager.loadAssertsFromFile(applicationContext)
        }

        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = true // true if your status bar background is light

            // Set system bar colors
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color(0xFF009688), // Use any color you like
                    darkIcons = useDarkIcons
                )
                systemUiController.setNavigationBarColor(
                    color = Color(0xFF020202),
                    darkIcons = useDarkIcons
                )
            }
            App()

//            com.sanju.jetpackclass.ui.theme.JetpackClassTheme {
//                QuoteDetail()
////                Scaffold(
////                    content = { paddingValues ->
////                        Column(
////                            modifier = Modifier
////                                .fillMaxSize()
////                                .padding(paddingValues)
////                                .padding(WindowInsets.systemBars.asPaddingValues()),
////                            verticalArrangement = Arrangement.Center,
////                            horizontalAlignment = Alignment.CenterHorizontally
////                        ) {
////                            QuoteDetail()
////                        }
////                    }
////                )
//            }
        }
    }
}

@Composable
fun App() {
    if (DataManager.isDataLoaded.value){
        if (DataManager.currentPage.value == Pages.LISTING){
            QuoteListScreen(data = DataManager.data) {
                DataManager.switchPages(it)
            }
        }
        else{
            DataManager.currentQuote?.let { QuoteDetail(quote = it) }
        }
    }
    else{
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(1f)
        ){
            Text(text = "Loading...",
                style = MaterialTheme.typography.labelLarge)
        }
    }
}

enum class Pages{
    LISTING,
    DETAIL
}
