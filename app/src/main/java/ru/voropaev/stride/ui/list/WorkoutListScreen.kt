package ru.voropaev.stride.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.voropaev.stride.data.model.ActivityType
import ru.voropaev.stride.data.model.Workout

@Composable
fun WorkoutListScreen(
    workouts: List<Workout>,
    onWorkoutClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(workouts) { workout ->
            Column(
                modifier = Modifier
                    .clickable {
                        onWorkoutClick(workout.id)
                    }
                    .padding(16.dp)
            ) {
                Text(workout.name)
                Text(activityTypeToStr(workout.activityType))
            }

        }
    }
}

fun activityTypeToStr(activityType: ActivityType): String = when (activityType) {
    ActivityType.WALKING -> "Ходьба"
    ActivityType.CYCLING -> "Езда на велосипеде"
    ActivityType.RUNNING -> "Бег"
}
