package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.foundation.rotary.RotaryScrollableDefaults.behavior
import androidx.wear.compose.foundation.rotary.rotaryScrollable
import androidx.wear.compose.material.Text
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.horologist.compose.layout.fillMaxRectangle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePageSuccessState(uiState: ProfilePageState.Success,
                            scrollState: ScrollState) {
    val focusRequester = rememberActiveFocusRequester()

    Column(
        modifier =
        Modifier
            .verticalScroll(scrollState)
            .rotaryScrollable(
                behavior(
                    scrollableState = scrollState,
                    flingBehavior = ScrollableDefaults.flingBehavior()
                ),
                focusRequester = focusRequester
            )
    ) {
        GlideImage(
            model = uiState.user.avatar_url,
            contentDescription = "Description",
            modifier = Modifier.fillMaxRectangle(),
        )
        Text(uiState.user.login)
        Text(uiState.user.login)
        Text(uiState.user.login)
        Text(uiState.user.login)
        Text(uiState.user.login)
        Text(uiState.user.login)
    }

}