package udg.cuvalles.mynavegation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    userId: String,
    userName: String,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile",
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.size(16.dp))

        Text(text = "ID: $userId", fontSize = 18.sp)
        Text(text = "Name: $userName", fontSize = 18.sp)
        Spacer(modifier = Modifier.size(16.dp))

        Button(onClick = onBack) {
            Text("Regresar")
        }
    }
}
