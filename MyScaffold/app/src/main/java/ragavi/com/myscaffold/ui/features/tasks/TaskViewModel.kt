package ragavi.com.myscaffold.ui.features.tasks

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import ragavi.com.myscaffold.data.Task

class TaskViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>(
        Task("1", "Comprar víveres", "Ir al supermercado a comprar fruta y verdura", false),
        Task("2", "Estudiar Jetpack Compose", "Revisar conceptos de Estado Mutable y Recomposición", true)
    )

    fun addTask(title: String, description: String, isCompleted: Boolean = false) {
        val newId = (tasks.size + 1).toString()
        tasks.add(Task(id = newId, title = title, description = description, initialCompleted = isCompleted))
    }

    fun toggleTask(task: Task) {
        task.isCompleted = !task.isCompleted
    }
}
