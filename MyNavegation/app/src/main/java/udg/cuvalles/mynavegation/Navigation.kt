package udg.cuvalles.mynavegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute

@Composable
fun Navigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ){
        composable<Home>{
            HomeScreen(
                onNavigateToProfile = { userId, userName ->
                    navController.navigate(Profile(userId, userName))
                }
            )
        }
        composable<Profile>{ backStackEntry ->
            val profileArgs: Profile = backStackEntry.toRoute()

            ProfileScreen(
                userId = profileArgs.userId,
                userName = profileArgs.userName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
