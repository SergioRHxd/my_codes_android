package ragavi.com.myscaffold.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Task(
    val id: String,
    val title: String,
    val description: String,
    initialCompleted: Boolean = false
) {
    var isCompleted by mutableStateOf(initialCompleted)
}
