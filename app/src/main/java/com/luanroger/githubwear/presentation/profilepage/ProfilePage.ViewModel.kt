package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanroger.githubwear.services.GitHubApi
import com.luanroger.githubwear.services.User
import kotlinx.coroutines.launch

sealed interface ProfilePageState {
    data object Loading: ProfilePageState
    data class Success(val user: User): ProfilePageState
    data class Error(val message: String): ProfilePageState
}

class ProfilePageViewModel: ViewModel() {
    var uiState: ProfilePageState by mutableStateOf(ProfilePageState.Loading)
        private set

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