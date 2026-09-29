package udg.cuvalles.mylazylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

data class FloatingHeart(
    val id: Long,
    val xOffset: Float
)

@Composable
fun ListaPersonasScreen(modifier: Modifier = Modifier) {
    val listaPersonas = remember {
        arrayListOf(
            Persona("Sergio Rodríguez", "Desarrollador & Estudiante TICs", 5, R.drawable.steve),
            Persona("Ana Martínez", "Diseñadora UI/UX", 12, R.drawable.enderman),
            Persona("Carlos Gómez", "Administrador de Redes", 3, R.drawable.creeper),
            Persona("Francisco Garcia", "Sexoservidor", 3, R.drawable.goku),
            Persona("Michael Jackson", "Artista/Compositor", 10000000, R.drawable.naruto),
            Persona("Juan Perez", "Stripper", 1, R.drawable.lich),
        )
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(listaPersonas) { persona ->
            PersonaCard(persona = persona)
        }
    }
}

@Composable
fun FloatingHeartView(heart: FloatingHeart) {
    var heartAnim by remember { mutableFloatStateOf(0f) }
    var heartAlpha by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(heart.id) {
        animate(
            initialValue = 0f,
            targetValue = -120f,
            animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing)
        ) { value, _ ->
            heartAnim = value
        }
    }
    LaunchedEffect(heart.id) {
        animate(
            initialValue = 1f,
            targetValue = 0f,
            animationSpec = tween(durationMillis = 800, easing = LinearEasing)
        ) { value, _ ->
            heartAlpha = value
        }
    }

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Floating Heart",
        tint = Color.Red,
        modifier = Modifier
            .offset(x = heart.xOffset.dp, y = heartAnim.dp)
            .size(28.dp)
            .graphicsLayer {
                alpha = heartAlpha
                scaleX = 1f - (heartAnim / 200f)
                scaleY = 1f - (heartAnim / 200f)
            }
    )
}

@Composable
fun PersonaCard(persona: Persona) {
    var contLikes by remember { mutableIntStateOf(persona.likes) }
    var showDetails by remember { mutableStateOf(true) }
    var isSelected by remember { mutableStateOf(false) }
    var iconScale by remember { mutableFloatStateOf(1f) }
    var iconRotation by remember { mutableFloatStateOf(0f) }
    var picScale by remember { mutableFloatStateOf(1f) }
    val coroutineScope = rememberCoroutineScope()

    // Floating hearts animation state
    var floatingHearts by remember { mutableStateOf(listOf<FloatingHeart>()) }

    // Card press interaction
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val cardScale by animateFloatAsState(
        targetValue = if (isPressed) 0.96f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "CardPressScale"
    )

    // Trigger explosive animations on like
    val handleLike: () -> Unit = {
        contLikes++
        // Add floating heart
        val newHeart = FloatingHeart(
            id = System.nanoTime(),
            xOffset = (-40..40).random().toFloat()
        )
        floatingHearts = floatingHearts + newHeart

        coroutineScope.launch {
            // Heart punch & rotation sequence
            iconScale = 1.7f
            iconRotation = 25f
            picScale = 1.18f
            delay(120L)
            iconScale = 0.85f
            iconRotation = -20f
            picScale = 0.95f
            delay(120L)
            iconScale = 1.3f
            iconRotation = 10f
            picScale = 1.08f
            delay(120L)
            iconScale = 1f
            iconRotation = 0f
            picScale = 1f
        }
    }

    // Auto-cleanup floating hearts after animation
    LaunchedEffect(floatingHearts.size) {
        if (floatingHearts.isNotEmpty()) {
            delay(900L)
            floatingHearts = floatingHearts.drop(1)
        }
    }

    // Animación de elevación de la tarjeta según los likes o selección
    val animatedElevation by animateDpAsState(
        targetValue = if (isSelected || contLikes > 5) 16.dp else 6.dp,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "ElevationAnimation"
    )

    // Infinite shimmer border for popular cards (likes > 5)
    val infiniteTransition = rememberInfiniteTransition(label = "GlowTransition")
    val shimmerAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ShimmerAlpha"
    )

    val borderColor = when {
        isSelected -> MaterialTheme.colorScheme.tertiary
        contLikes > 5 -> MaterialTheme.colorScheme.primary.copy(alpha = shimmerAlpha)
        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
    }

    // Animación de color de fondo cuando está seleccionado o tiene likes
    val animatedColor by animateColorAsState(
        targetValue = when {
            isSelected -> MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f)
            contLikes > 0 -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.25f)
            else -> MaterialTheme.colorScheme.surface
        },
        animationSpec = tween(durationMillis = 500),
        label = "BackgroundColor"
    )

    val animatedPicScale by animateFloatAsState(
        targetValue = picScale,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "ProfilePicScale"
    )

    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
        colors = CardDefaults.cardColors(containerColor = animatedColor),
        modifier = Modifier
            .fillMaxWidth()
            .scale(cardScale)
            .clickable { isSelected = !isSelected }
            .border(
                width = if (isSelected || contLikes > 5) 3.dp else 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(24.dp)
            )
            .shadow(
                elevation = if (isSelected || contLikes > 5) 12.dp else 2.dp,
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                // Header banner solid black
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(95.dp)
                        .background(Color.Black)
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        if (contLikes > 5) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Featured",
                                tint = Color.Yellow,
                                modifier = Modifier
                                    .size(18.dp)
                                    .scale(shimmerAlpha)
                            )
                        }
                        Text(
                            text = if (contLikes > 5) "¡VIP!" else "Perfil",
                            color = Color.White,
                            fontSize = 13.sp,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }

                // Profile Image with bounce and glowing border
                Image(
                    painter = painterResource(id = persona.foto),
                    contentDescription = "Foto de Perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(96.dp)
                        .align(Alignment.BottomCenter)
                        .scale(animatedPicScale)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .border(
                            width = 4.dp,
                            brush = Brush.sweepGradient(
                                listOf(
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.secondary,
                                    MaterialTheme.colorScheme.tertiary,
                                    MaterialTheme.colorScheme.primary
                                )
                            ),
                            shape = CircleShape
                        )
                )

                // Floating Hearts Animation Overlay
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentAlignment = Alignment.Center
                ) {
                    floatingHearts.forEach { heart ->
                        key(heart.id) {
                            FloatingHeartView(heart = heart)
                        }
                    }
                }
            }

            // Name, Occupation with Show/Hide toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = persona.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    
                    AnimatedVisibility(
                        visible = showDetails,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Text(
                            text = persona.ocupacion,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                IconButton(onClick = { showDetails = !showDetails }) {
                    Icon(
                        imageVector = if (showDetails) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Mostrar/Ocultar detalles",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = handleLike,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Like",
                        modifier = Modifier
                            .size(22.dp)
                            .scale(iconScale)
                            .rotate(iconRotation),
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    // Slot-machine style rolling counter animation
                    AnimatedContent(
                        targetState = contLikes,
                        transitionSpec = {
                            if (targetState > initialState) {
                                (slideInVertically { height -> height } + fadeIn())
                                    .togetherWith(slideOutVertically { height -> -height } + fadeOut())
                            } else {
                                (slideInVertically { height -> -height } + fadeIn())
                                    .togetherWith(slideOutVertically { height -> height } + fadeOut())
                            }
                        },
                        label = "LikeCounterAnimation"
                    ) { targetLikes ->
                        Text(
                            text = "Me gusta ($targetLikes)",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }
                }

                OutlinedButton(
                    onClick = { contLikes = 0 },
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.error)
                ) {
                    Text(
                        text = "Reiniciar",
                        color = MaterialTheme.colorScheme.error
                    )
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
