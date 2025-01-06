/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package com.luanroger.githubwear.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController
import com.google.android.horologist.compose.layout.AppScaffold
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
            val model: ProfilePageViewModel = viewModel()

            SwipeDismissableNavHost(navController, startDestination = "home") {
                composable("home") { HomePage({
                    navController.navigate("profile/{username}")
                }) }
                composable("profile/{username}") { navBackStackEntry ->
                    ProfilePage(uiState = model.uiState)
                }
            }
        }
    }
}