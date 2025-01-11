package com.luanroger.githubwear.presentation.profilepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luanroger.githubwear.services.GitHubApi
import com.luanroger.githubwear.services.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface ProfilePageState {
    data object Loading: ProfilePageState
    data class Success(val user: User): ProfilePageState
    data class Error(val message: String): ProfilePageState
}

class ProfilePageViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<ProfilePageState>(ProfilePageState.Loading)
    val uiState: StateFlow<ProfilePageState> = _uiState.asStateFlow()


    fun getUserInfo(username: String) {
        Log.v("ProfilePageViewModel", "getUserInfo: $username")
        viewModelScope.launch {
            _uiState.value = ProfilePageState.Loading

            _uiState.value = try {
                val response = GitHubApi.service.getUserInfo(username)
                ProfilePageState.Success(response)
            } catch (e: Exception) {
                Log.e("ProfilePageViewModel", "getUserInfo: $e")
                ProfilePageState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}