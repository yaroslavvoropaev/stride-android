package ru.voropaev.stride.data.model


import java.time.Duration
import java.time.Instant


data class Workout(
    val id: Long,
    val activityType: ActivityType,
    val name: String = activityType.toString(),
    val dateOfStart: Instant,
    val distanceMetres: Int,
    val duration: Duration,
    val splits: List<Split>
) {
    val avgPaceSecPerKm: Long?
        get() = if (distanceMetres > 0) {
            duration.seconds * 1000 / distanceMetres
        } else {
            null
        }
}
