package ragavi.com.myscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ragavi.com.myscaffold.ui.navigation.AppNavigation
import ragavi.com.myscaffold.ui.theme.MyScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyScaffoldTheme {
                AppNavigation()
            }
        }
    }
}
