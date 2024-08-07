package com.livelike.stopwatch

data class StopwatchState(
    val elapsedTime: Long = 0L,
    val isRunning: Boolean = false
)