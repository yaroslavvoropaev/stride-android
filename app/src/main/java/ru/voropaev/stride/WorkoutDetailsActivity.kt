package ru.voropaev.stride

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.net.toUri
import ru.voropaev.stride.data.AppContainer
import ru.voropaev.stride.data.model.Workout
import ru.voropaev.stride.link.WorkoutLink
import ru.voropaev.stride.ui.details.NotFoundScreen
import ru.voropaev.stride.ui.details.WorkoutDetailsScreen
import ru.voropaev.stride.ui.theme.StrideTheme

import java.io.Serializable

private const val KEY_STATE = "detailsState"

class WorkoutDetailsActivity : LifecycleLoggingActivity() {
    private data class DetailsState(val link: String?, val showSplits: Boolean) : Serializable
    private val showSplits = mutableStateOf(false)
    private val link = mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val saved  = savedInstanceState?.getSerializable(KEY_STATE) as? DetailsState
        link.value = saved?.link?.toUri()  ?: intent.data
        showSplits.value = saved?.showSplits ?: false
        enableEdgeToEdge()
        setContent {
            val workout: Workout? =
                WorkoutLink.parse(link.value)?.let { AppContainer.workoutRepository.getById(it) }
            StrideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (workout != null) {
                        WorkoutDetailsScreen(
                            workout = workout,
                            modifier = Modifier.padding(innerPadding),
                            showSplits = showSplits.value,
                            onToggleSplits = { showSplits.value = !showSplits.value },
                            onShareClick = { shareWorkout(workout) },
                        )
                    } else {
                        NotFoundScreen(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(KEY_STATE, DetailsState(
            link.value?.toString(),
            showSplits.value
        ))
        super.onSaveInstanceState(outState)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        link.value = intent?.data
        showSplits.value = false
    }

    private fun shareWorkout(workout: Workout) {
        val text =
            "Моя тренировка «${workout.name}» в Stride: ${WorkoutLink.createLink(workout.id)}"
        val intent: Intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, text)

        startActivity(Intent.createChooser(intent, "Поделиться тренировкой"))
    }
}