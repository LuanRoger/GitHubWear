package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.layout.fillMaxRectangle

@Composable
fun ProfilePageErrorState(uiState: ProfilePageState.Error) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxRectangle()
    ) {
        Text((uiState).message)
    }
}