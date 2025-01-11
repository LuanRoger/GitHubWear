package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.android.horologist.compose.layout.fillMaxRectangle

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePageSuccessState(uiState: ProfilePageState.Success) {
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