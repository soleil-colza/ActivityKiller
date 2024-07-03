package com.example.activitykiller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.activitykiller.ui.theme.ActivityKillerTheme
import com.example.activitykiller.ui.viewmodel.MessageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityKillerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel: MessageViewModel = viewModel()
                    val message = viewModel.message.observeAsState("Android") // デフォルト値を設定

                    if (savedInstanceState != null) {
                        viewModel.setMessage("RIP")
                    }

                    Greeting(message.value)
                }
            }
        }
    }
}

@Composable
fun Greeting(message: String) {
    Text(text = message)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActivityKillerTheme {
        Greeting("Android")
    }
}
