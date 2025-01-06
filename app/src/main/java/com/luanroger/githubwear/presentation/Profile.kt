package com.luanroger.githubwear.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.wear.compose.material.Text
import com.luanroger.githubwear.presentation.services.GitHubApi
import kotlinx.coroutines.launch

sealed interface ProfilePageState {
    object Loading: ProfilePageState
    data class Success(val user: String): ProfilePageState
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

@Composable
fun ProfilePage(uiState: ProfilePageState) {
    when(uiState) {
        is ProfilePageState.Loading -> {
            Column {
                Text("Loading...")
            }
        }
        is ProfilePageState.Success -> {
            Column {
                Text("User: ${uiState.user}")
            }
        }
        is ProfilePageState.Error -> {
            Column {
                Text("Error: ${uiState.message}")
            }
        }
    }
}