package com.livelike.stopwatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.livelike.stopwatch.ui.theme.StopWatchTheme

class StopWatchActivity:ComponentActivity() {

    private val viewModel: StopWatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StopWatchTheme {
                StopWatchScreen()
            }
        }

    }


    @Composable
    fun StopWatchScreen(){
        val stopWatchStateValue by viewModel.stopwatchState.collectAsStateWithLifecycle()
        val seconds = (stopWatchStateValue.elapsedTime / 1000) % 60
        val minutes = (stopWatchStateValue.elapsedTime / (1000 * 60)) % 60
        val hours = (stopWatchStateValue.elapsedTime / (1000 * 60 * 60)) % 24
        val milliseconds = (stopWatchStateValue.elapsedTime % 1000) / 100

        
        Column(modifier = Modifier.fillMaxSize().
        background(Color.LightGray)
            .padding(16.dp)

        ) {
            Text(text = String.format("%02d:%02d:%02d.%01d", hours, minutes, seconds, milliseconds))
            Button(onClick = { viewModel.start()}) {
                Text(text = "Start",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                    )
                
            }
            Button(onClick = { viewModel.stop()}) {
                Text(text = "Stop")

            }
            Button(onClick = { viewModel.reset()}) {
                Text(text = "Reset")

            }
        }

    }
}