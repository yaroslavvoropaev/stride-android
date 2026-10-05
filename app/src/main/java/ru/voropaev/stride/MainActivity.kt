package ru.voropaev.stride

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.voropaev.stride.data.AppContainer
import ru.voropaev.stride.link.WorkoutLink
import ru.voropaev.stride.ui.list.WorkoutListScreen
import ru.voropaev.stride.ui.theme.StrideTheme

class MainActivity : LifecycleLoggingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StrideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WorkoutListScreen(
                        workouts = AppContainer.workoutRepository.getAll(),
                        onWorkoutClick = {id ->
                            val intent = Intent(this, WorkoutDetailsActivity::class.java)
                            intent.data = WorkoutLink.createLink(id)
                            startActivity(intent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

