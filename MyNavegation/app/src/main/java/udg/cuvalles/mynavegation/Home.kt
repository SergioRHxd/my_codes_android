package udg.cuvalles.mynavegation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    onNavigateToProfile: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    var userId by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home App",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("User Id") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (userId.isNotEmpty() && userName.isNotEmpty()) {
                    onNavigateToProfile(userId, userName)
                }
            },
            enabled = userId.isNotEmpty() && userName.isNotEmpty()
        ) {
            Text("Navigate to Profile")
        }
    }
}
