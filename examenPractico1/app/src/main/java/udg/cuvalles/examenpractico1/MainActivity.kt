package udg.cuvalles.examenpractico1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import udg.cuvalles.examenpractico1.ui.theme.ExamenPractico1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExamenPractico1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    ComposableLayout()
                }
            }
        }
    }
}

@Composable
fun ComposableLayout() {
    val matrixGreen = Color(0xFF00FF00)
    val matrixDarkBg = Color(0xFF0B120B)

    val lazyRowImages = listOf(
        R.drawable.img_item1,
        R.drawable.img_item2,
        R.drawable.img_item3,
        R.drawable.img_item4,
        R.drawable.img_item5,
        R.drawable.img_item6,
        R.drawable.img_item7,
        R.drawable.img_item8
    )

    val animeGameCuriosities = listOf(
        "Pokémon: ¡Pikachu no iba a ser la mascota original! Clefairy fue la primera opción elegida por Game Freak.",
        "Dragon Ball: El diseño de Goku está inspirado en Sun Wukong, el Rey Mono de la mitología china.",
        "Minecraft: El Creeper nació de un error de programación al intentar crear el modelo de un cerdo.",
        "Naruto: Masashi Kishimoto basó Ichiraku Ramen en un puesto real que visitaba cuando era estudiante.",
        "The Legend of Zelda: Link es zurdo en la mayoría de juegos porque Shigeru Miyamoto es zurdo.",
        "Neon Genesis Evangelion: Los EVAs son en realidad cíclopes cibernéticos orgánicos clonados de Lilith.",
        "Super Mario Bros.: Mario salta porque en las primeras arcades no había suficiente memoria para caminar.",
        "Attack on Titan: Hajime Isayama se inspiró en los gigantes y borrachos que vio en un cibercafé.",
        "Cyberpunk 2077: Keanu Reeves interpretó a Johnny Silverhand y tiene el doble de líneas que cualquier otro.",
        "Death Note: Ryuk ama las manzanas porque en la mitología japonesa simbolizan tentación y misterio."
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. CARD PRINCIPAL
        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = matrixDarkBg),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, matrixGreen, RoundedCornerShape(16.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Imagen de perfil
                        Box(
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape)
                                .border(2.dp, matrixGreen, CircleShape)
                                .background(Color.Black)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = "Profile Picture",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        // Títulos / Subtexto
                        Column {
                            Text(
                                text = "Matrix Geeks Hub",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                color = matrixGreen
                            )
                            Text(
                                text = "Anime & Videogames\nCuriosities Feed",
                                fontSize = 14.sp,
                                color = Color.LightGray
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones inferiores
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        // Botón izquierdo (Outlined)
                        Box(
                            modifier = Modifier
                                .height(36.dp)
                                .width(90.dp)
                                .border(1.dp, matrixGreen, RoundedCornerShape(18.dp))
                                .background(Color.Black, RoundedCornerShape(18.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Explore", color = matrixGreen, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        // Botón derecho (Borde verde matrix)
                        Box(
                            modifier = Modifier
                                .height(36.dp)
                                .width(90.dp)
                                .border(2.dp, matrixGreen, RoundedCornerShape(18.dp))
                                .background(matrixGreen, RoundedCornerShape(18.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Connect", color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // 3. LAZYROW con imágenes adicionales
        item {
            Text(
                text = "Categorías / Iconos",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = matrixGreen
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(lazyRowImages) { imageRes ->
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .border(1.dp, matrixGreen, RoundedCornerShape(12.dp))
                            .background(matrixDarkBg, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Category Image",
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Fit
                        )
                    }
                }
            }
        }

        // Título antes de la lista vertical de curiosidades
        item {
            Text(
                text = "Curiosidades de Anime y Videojuegos",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = matrixGreen
            )
        }

        // 4. LAZYCOLUMN con textos de curiosidades
        items(animeGameCuriosities) { curiosity ->
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = matrixDarkBg),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, matrixGreen.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(matrixGreen, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = curiosity,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}
