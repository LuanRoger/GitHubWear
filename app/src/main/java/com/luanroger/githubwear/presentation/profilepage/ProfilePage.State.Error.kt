package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.layout.fillMaxRectangle

@Composable
fun ProfilePageErrorState(uiState: ProfilePageState.Error) {
    Box(
        modifier = Modifier.fillMaxRectangle()
    ) {
        Text((uiState).message)
    }
}