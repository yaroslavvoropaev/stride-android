package ru.voropaev.stride.data

import ru.voropaev.stride.data.repository.FakeWorkoutRepository
import ru.voropaev.stride.data.repository.WorkoutRepository

object AppContainer {
    val workoutRepository: WorkoutRepository = FakeWorkoutRepository()
}