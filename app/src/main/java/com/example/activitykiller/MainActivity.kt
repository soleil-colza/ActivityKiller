package com.example.activitykiller

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
                    val message by viewModel.message.observeAsState("")

                    val aliveMessage = stringResource(id = R.string.alive)
                    val ripMessage = stringResource(id = R.string.rip)

                    if (message.isEmpty()) {
                        viewModel.setMessage(aliveMessage)
                    }

                    if (savedInstanceState != null) {
                        viewModel.setMessage(ripMessage)
                    }

                    MainScreen(
                        message = message,
                        onRestartClick = { restartActivity() }
                    )
                }
            }
        }
    }

    private fun restartActivity() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    message: String,
    onRestartClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = if (message == stringResource(id = R.string.rip)) "Activity is dead🪦" else "Kill Activity👊"
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimationView(message)
            Spacer(modifier = Modifier.height(16.dp))
            if (message == stringResource(id = R.string.rip)) {
                ElevatedButton(onClick = onRestartClick) {
                    Text("Restart Activity")
                }
            }
        }
    }
}

@Composable
fun LottieAnimationView(message: String) {
    val composition by rememberLottieComposition(
        when (message) {
            stringResource(id = R.string.rip) -> LottieCompositionSpec.RawRes(R.raw.dead_pink)
            else -> LottieCompositionSpec.RawRes(R.raw.alive)
        }
    )
    LottieAnimation(
        composition,
        iterations = LottieConstants.IterateForever,
        modifier = Modifier.size(200.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ActivityKillerTheme {
        MainScreen(
            message = "Android",
            onRestartClick = {}
        )
    }
}
