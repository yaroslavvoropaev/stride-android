package ru.voropaev.stride.data.repository

import ru.voropaev.stride.data.model.ActivityType
import ru.voropaev.stride.data.model.Split
import ru.voropaev.stride.data.model.Workout
import java.time.Duration
import java.time.Instant



class FakeWorkoutRepository : WorkoutRepository {

    private val workouts: List<Workout> = listOf(
        Workout(
            id = 1,
            activityType = ActivityType.CYCLING,
            name = "Тренировка утренняя",
            dateOfStart = Instant.parse("2026-09-04T19:41:00Z"),
            distanceMetres = 2700,
            duration = Duration.ofMinutes(67),
            splits = listOf(
                Split(
                    numberOfKilometer = 1,
                    distance = 1000,
                    duration = Duration.ofMinutes(20)
                ),
                Split(
                    numberOfKilometer = 2,
                    distance = 1000,
                    duration = Duration.ofMinutes(20)
                ),
                Split(
                    numberOfKilometer = 3,
                    distance = 700,
                    duration = Duration.ofMinutes(27)
                )
            )
        ),
        Workout(
            id = 2,
            activityType = ActivityType.RUNNING,
            name = "тренировка перед обедом",
            dateOfStart = Instant.parse("2026-11-04T19:41:00Z"),
            distanceMetres = 1200,
            duration = Duration.ofMinutes(10),
            splits = listOf(
                Split(
                    numberOfKilometer = 1,
                    distance = 1000,
                    duration = Duration.ofMinutes(5)
                ),
                Split(
                    numberOfKilometer = 2,
                    distance = 200,
                    duration = Duration.ofMinutes(5)
                )
            ),
        ),
        Workout(
            id = 3,
            activityType = ActivityType.WALKING,
            name = "тренировка после обеда",
            dateOfStart = Instant.parse("2026-10-04T19:41:00Z"),
            distanceMetres = 800,
            duration = Duration.ofMinutes(25),
            splits = listOf(
                Split(
                    numberOfKilometer = 1,
                    distance = 800,
                    duration = Duration.ofMinutes(25)
                )
            )
        )
    )

    override fun getAll(): List<Workout> {
        return workouts
    }

    override fun getById(id: Long): Workout? {
        return workouts.find { it.id == id }
    }
}