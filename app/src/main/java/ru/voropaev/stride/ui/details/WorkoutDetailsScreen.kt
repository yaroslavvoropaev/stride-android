package ru.voropaev.stride.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.voropaev.stride.R
import ru.voropaev.stride.data.model.Workout
import ru.voropaev.stride.ui.list.activityTypeToStr

@Composable
fun WorkoutDetailsScreen(
    workout: Workout,
    modifier: Modifier = Modifier,
    showSplits: Boolean,
    onToggleSplits: () -> Unit,
    onShareClick: () -> Unit
) {

    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item { Text(workout.name, style = MaterialTheme.typography.headlineSmall) }

        item {
            Button(onClick = onShareClick) {
                Text("Поделиться")
            }
        }

        item { Text(activityTypeToStr(workout.activityType)) }
        item { Text("Дата: ${workout.dateOfStart}") }
        item { Text("Дистанция: ${workout.distanceMetres} м") }
        item { Text("Время: ${workout.duration.toMinutes()} мин") }
        item { Text("Темп: ${workout.avgPaceSecPerKm ?: "-"} с/км") }

        item {
            Button(
                onClick = onToggleSplits,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(if (showSplits) "Скрыть сплиты" else "Показать сплиты")
            }
        }

        if (showSplits) {
            items(workout.splits) { split ->
                Text("${split.numberOfKilometer} км: ${split.distance} м за ${split.duration.toMinutes()} мин")
            }
        }
    }
}

@Composable
fun NotFoundScreen(modifier : Modifier = Modifier) {
    Text(
        stringResource(R.string.workout_not_found),
        modifier = modifier.padding(top = 16.dp)
    )
}
