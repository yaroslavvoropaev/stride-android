package ru.voropaev.stride.data.repository

import ru.voropaev.stride.data.model.Workout

interface WorkoutRepository {
    fun getAll(): List<Workout>
    fun getById(id: Long): Workout?
}