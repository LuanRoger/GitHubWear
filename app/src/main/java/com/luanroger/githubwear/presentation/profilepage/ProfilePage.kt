package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.horologist.compose.layout.ScreenScaffold

@Composable
fun ProfilePage(username: String) {
    val viewModel: ProfilePageViewModel = viewModel()
    val scrollState = rememberScrollState()

    LaunchedEffect(username) {
        viewModel.getUserInfo(username)
    }

    val uiState = viewModel.uiState.collectAsState().value

    ScreenScaffold(scrollState = scrollState) {
        when(uiState) {
            ProfilePageState.Loading -> {
                ProfilePageLoadingState()
            }
            is ProfilePageState.Success -> {
                ProfilePageSuccessState(uiState, scrollState)
            }
            is ProfilePageState.Error -> {
                ProfilePageErrorState(uiState)
            }
        }
    }
}