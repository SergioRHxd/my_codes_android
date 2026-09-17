package udg.cuvalles.mylazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udg.cuvalles.mylazylist.ui.theme.MyLazyListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyLazyListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaPersonasScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ListaPersonasScreen(modifier: Modifier = Modifier) {
    // Instanciación del arrayListOf con al menos 3 personas
    val listaPersonas = remember {
        arrayListOf(
            Persona("Sergio Rodríguez", "Desarrollador & Estudiante TICs", 5),
            Persona("Ana Martínez", "Diseñadora UI/UX", 12),
            Persona("Carlos Gómez", "Administrador de Redes", 3),
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(listaPersonas) { persona ->
            PersonaCard(persona = persona)
        }
    }
}

@Composable
fun PersonaCard(persona: Persona) {
    var contLikes by remember { mutableIntStateOf(persona.likes) }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                        .background(MaterialTheme.colorScheme.primaryContainer)
                ) {
                    Text(
                        text = "Perfil",
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    )
                }

                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Foto de Perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.BottomCenter)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .border(3.dp, MaterialTheme.colorScheme.surface, CircleShape)
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = persona.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = persona.ocupacion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(onClick = { contLikes++ }) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Like",
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Me gusta ($contLikes)")
                }
                Button(onClick = { contLikes = 0 }) {
                    Text("Reiniciar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaPersonasPreview() {
    MyLazyListTheme {
        ListaPersonasScreen()
    }
}