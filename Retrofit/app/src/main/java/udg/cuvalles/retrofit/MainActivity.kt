package udg.cuvalles.retrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import udg.cuvalles.retrofit.ui.theme.RetrofitTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CoffeeScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CoffeeScreen(
    modifier: Modifier = Modifier,
    viewModel: PostViewModel = viewModel()
) {
    Surface(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator()
                }
                viewModel.errorMessage != null -> {
                    Text(
                        text = "Error: ${viewModel.errorMessage}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                viewModel.coffeeList.isEmpty() -> {
                    Text(
                        text = "La lista de cafés está vacía.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(viewModel.coffeeList) { coffee ->
                            CoffeeCard(coffee = coffee)
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CoffeeCard(coffee: CoffeeItem) {
    val translatedTitle = CoffeeTranslator.translateTitle(coffee.title)
    val translatedDescription = CoffeeTranslator.translateDescription(coffee.title, coffee.description)
    val translatedIngredients = CoffeeTranslator.translateIngredients(coffee.ingredients)

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (!coffee.image.isNullOrEmpty()) {
                var aspectRatio by remember { mutableStateOf(16f / 9f) }
                SubcomposeAsyncImage(
                    model = coffee.image,
                    contentDescription = translatedTitle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(aspectRatio),
                    contentScale = ContentScale.FillWidth
                ) {
                    val state = painter.state
                    if (state is AsyncImagePainter.State.Success) {
                        val intrinsicWidth = state.painter.intrinsicSize.width
                        val intrinsicHeight = state.painter.intrinsicSize.height
                        if (intrinsicWidth > 0 && intrinsicHeight > 0) {
                            aspectRatio = intrinsicWidth / intrinsicHeight
                        }
                    }
                    SubcomposeAsyncImageContent()
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = translatedTitle,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = translatedDescription,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (translatedIngredients.isNotEmpty()) {
                    Text(
                        text = "Ingredientes: ${translatedIngredients.joinToString(", ")}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeScreenPreview() {
    RetrofitTheme {
        CoffeeScreen()
    }
}
