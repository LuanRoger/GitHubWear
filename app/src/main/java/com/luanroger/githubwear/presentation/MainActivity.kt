package com.luanroger.githubwear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.compose.layout.AppScaffold
import com.luanroger.githubwear.presentation.homepage.HomePage
import com.luanroger.githubwear.presentation.profilepage.ProfilePage
import com.luanroger.githubwear.presentation.profilepage.ProfilePageViewModel
import com.luanroger.githubwear.presentation.theme.GitHubWearTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        setTheme(android.R.style.Theme_DeviceDefault)

        setContent {
            WearApp()
        }
    }
}

@Composable
fun WearApp() {
    GitHubWearTheme {
        AppScaffold {
            val navController = rememberSwipeDismissableNavController()

            SwipeDismissableNavHost(navController, startDestination = "home") {
                composable("home") { HomePage { username ->
                    navController.navigate("profile/${username}")
                }
                }
                composable("profile/{username}",
                    arguments = listOf(navArgument("username") { type = NavType.StringType }))
                { navBackStackEntry ->
                    val username = navBackStackEntry.arguments?.getString("username") ?:
                    "LuanRoger"

                    ProfilePage(username)
                }
            }
        }
    }
}