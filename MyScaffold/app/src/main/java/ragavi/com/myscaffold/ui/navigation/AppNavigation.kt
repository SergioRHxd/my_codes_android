package ragavi.com.myscaffold.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import ragavi.com.myscaffold.ui.features.tasks.TaskFormScreen
import ragavi.com.myscaffold.ui.features.tasks.TaskList
import ragavi.com.myscaffold.ui.features.tasks.TaskViewModel

enum class Screen {
    List, Create
}

@Composable
fun AppNavigation() {
    val taskViewModel: TaskViewModel = viewModel()

    var currentScreen by remember { mutableStateOf(Screen.List) }

    when (currentScreen) {
        Screen.List -> TaskList(
            viewModel = taskViewModel,
            onNavigateToCreate = { currentScreen = Screen.Create }
        )
        Screen.Create -> TaskFormScreen(
            viewModel = taskViewModel,
            onNavigateBack = { currentScreen = Screen.List }
        )
    }
}
