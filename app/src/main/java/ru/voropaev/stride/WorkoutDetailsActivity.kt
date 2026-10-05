package ru.voropaev.stride

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.voropaev.stride.data.AppContainer
import ru.voropaev.stride.data.model.Workout
import ru.voropaev.stride.link.WorkoutLink
import ru.voropaev.stride.ui.details.NotFoundScreen
import ru.voropaev.stride.ui.details.WorkoutDetailsScreen
import ru.voropaev.stride.ui.theme.StrideTheme

class WorkoutDetailsActivity : LifecycleLoggingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id: Long? = WorkoutLink.parse(intent.data)
        val workout: Workout? = id?.let { AppContainer.workoutRepository.getById(id) }
        enableEdgeToEdge()
        setContent {
            StrideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (workout != null) {
                        WorkoutDetailsScreen(workout = workout, modifier = Modifier.padding(innerPadding))
                    } else {
                        NotFoundScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}