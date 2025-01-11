package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.foundation.rotary.RotaryScrollableDefaults.behavior
import androidx.wear.compose.foundation.rotary.rotaryScrollable
import com.google.android.horologist.compose.layout.ScreenScaffold

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
                    ProfilePageLoadingState()
                }
                is ProfilePageState.Success -> {
                    ProfilePageSuccessState(uiState)
                }
                is ProfilePageState.Error -> {
                    ProfilePageErrorState(uiState)
                }
            }
        }
    }
}