package com.livelike.stopwatch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StopWatchViewModel: ViewModel() {

    private val _stopwatchState = MutableStateFlow(StopwatchState())
    val stopwatchState: StateFlow<StopwatchState> = _stopwatchState


    private var startTime: Long = 0L

    fun start() {
        if (_stopwatchState.value.isRunning) return

        startTime = System.currentTimeMillis() - _stopwatchState.value.elapsedTime
        _stopwatchState.value = _stopwatchState.value.copy(isRunning = true)

        viewModelScope.launch {
            while (_stopwatchState.value.isRunning) {
                val newElapsedTime = System.currentTimeMillis() - startTime
                _stopwatchState.value = _stopwatchState.value.copy(elapsedTime = newElapsedTime)
                delay(100)
            }
        }
    }


    fun stop() {
        if (!_stopwatchState.value.isRunning) return

        _stopwatchState.value = _stopwatchState.value.copy(isRunning = false)
    }


    fun reset() {
        _stopwatchState.value = StopwatchState()
    }
}