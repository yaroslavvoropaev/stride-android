package ru.voropaev.stride.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
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
    onToggleSplits: () -> Unit
) {

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(workout.name, style = MaterialTheme.typography.headlineSmall)
        Text(activityTypeToStr(workout.activityType))
        Text("Дата: ${workout.dateOfStart}")
        Text("Дистанция: ${workout.distanceMetres} м")
        Text("Время: ${workout.duration.toMinutes()} мин")
        Text("Темп: ${workout.avgPaceSecPerKm ?: "-"} с/км")

        Button(
            onClick = onToggleSplits,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(if (showSplits) "Скрыть сплиты" else "Показать сплиты")
        }
        if (showSplits) {
            workout.splits.forEach { split ->
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
