package com.example.activitykiller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
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

                    LottieAnimationView(message.value)
                }
            }
        }
    }
}

@Composable
fun LottieAnimationView(message: String) {
    val composition by rememberLottieComposition(
        when (message) {
            "RIP" -> LottieCompositionSpec.RawRes(R.raw.dead_pink)
            else -> LottieCompositionSpec.RawRes(R.raw.alive)
        }
    )
    LottieAnimation(
        composition,
        iterations = LottieConstants.IterateForever
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActivityKillerTheme {
        LottieAnimationView("Android")
    }
}
