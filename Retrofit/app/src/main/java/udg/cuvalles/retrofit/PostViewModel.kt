package udg.cuvalles.retrofit

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {
    var coffeeList by mutableStateOf<List<CoffeeItem>>(emptyList())
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchCoffees()
    }

    fun fetchCoffees() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val allCoffees = ApiServiceCoffes.instance.getHotCoffees()
                // Drop the last 4 items as requested
                coffeeList = if (allCoffees.size > 4) {
                    allCoffees.dropLast(4)
                } else {
                    allCoffees
                }
                Log.d("PostViewModel", "Loaded ${coffeeList.size} coffees")
            } catch (e: Exception) {
                errorMessage = e.localizedMessage ?: e.toString()
                Log.e("PostViewModel", "Error fetching coffees: ${e.message}", e)
            } finally {
                isLoading = false
            }
        }
    }
}
