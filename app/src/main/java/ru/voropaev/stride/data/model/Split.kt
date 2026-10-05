package ru.voropaev.stride.data.model

import java.time.Duration

data class Split(
    val numberOfKilometer: Int,
    val distance: Int,
    val duration: Duration
)
