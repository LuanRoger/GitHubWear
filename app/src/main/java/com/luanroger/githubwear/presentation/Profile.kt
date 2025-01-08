package com.luanroger.githubwear.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.foundation.rotary.RotaryScrollableDefaults.behavior
import androidx.wear.compose.foundation.rotary.rotaryScrollable
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.google.android.horologist.compose.layout.fillMaxRectangle
import com.luanroger.githubwear.presentation.services.GitHubApi
import com.luanroger.githubwear.presentation.services.User
import kotlinx.coroutines.launch

sealed interface ProfilePageState {
    object Loading: ProfilePageState
    data class Success(val user: User): ProfilePageState
    data class Error(val message: String): ProfilePageState
}

class ProfilePageViewModel: ViewModel() {
    var uiState: ProfilePageState by mutableStateOf(ProfilePageState.Loading)
        private set

    init {
        getUserInfo("LuanRoger")
    }

    fun getUserInfo(username: String) {
        viewModelScope.launch {
            uiState = ProfilePageState.Loading

            uiState = try {
                val response = GitHubApi.service.getUserInfo(username)
                ProfilePageState.Success(response)
            } catch (e: Exception) {
                ProfilePageState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePage(uiState: ProfilePageState) {
    val focusRequester = rememberActiveFocusRequester()
    val scrollState = rememberScrollState()

    ScreenScaffold(scrollState = scrollState) {
        Column(
            modifier =
            Modifier.verticalScroll(scrollState)
                .rotaryScrollable(
                    behavior(
                        scrollableState = scrollState,
                        flingBehavior = ScrollableDefaults.flingBehavior()
                    ),
                    focusRequester = focusRequester
                )
        ) {
            when(uiState) {
                ProfilePageState.Loading -> {
                    Text("Loading...")
                }
                is ProfilePageState.Success -> {
                    GlideImage(
                        model = uiState.user.avatar_url,
                        contentDescription = "Descriptiom",
                        modifier = Modifier.fillMaxRectangle(),
                    )
                    Text(uiState.user.login)
                    Text(uiState.user.login)
                    Text(uiState.user.login)
                    Text(uiState.user.login)
                    Text(uiState.user.login)
                    Text(uiState.user.login)

                }
                is ProfilePageState.Error -> {
                    Text((uiState).message)
                }
            }
        }
    }
}