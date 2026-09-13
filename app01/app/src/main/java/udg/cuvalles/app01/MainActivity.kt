package udg.cuvalles.app01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udg.cuvalles.app01.ui.theme.App01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileCardScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileCardScreen(modifier: Modifier = Modifier) {
    // Variables de estado para editar en tiempo real
    var titleText by remember { mutableStateOf("Sergio Rodríguez") }
    var subText by remember { mutableStateOf("Desarrollador & Estudiante de TICs") }
    var contLikes by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // --- 1. TARJETA DE PERFIL ---
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Box para encimar la foto de perfil en la imagen/fondo de portada
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp),
                    contentAlignment = Alignment.TopCenter
                ) {
                    // Fondo superior (simula encabezado)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(
                            text = "Portada",
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                        )
                    }

                    // Icon / Image circular de perfil
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

                // Textos editables dentro de la tarjeta
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = titleText,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = subText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                // Row con dos botones en la parte inferior
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = { contLikes++ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart),
                            contentDescription = "Like",
                            modifier = Modifier.size(20.dp),
                            tint = Color.Unspecified // Usar el color definido en el XML o el default
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

        // --- 2. CONTROLES INTERACTIVOS (TextFields) ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Controles Interactivos",
                    style = MaterialTheme.typography.titleSmall
                )

                // TextField para cambiar el nombre/título
                OutlinedTextField(
                    value = titleText,
                    onValueChange = { titleText = it },
                    label = { Text("Editar Título / Nombre") },
                    leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth()
                )

                // TextField para cambiar la descripción
                OutlinedTextField(
                    value = subText,
                    onValueChange = { subText = it },
                    label = { Text("Editar Descripción / Subtexto") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardPreview() {
    App01Theme {
        ProfileCardScreen()
    }
}