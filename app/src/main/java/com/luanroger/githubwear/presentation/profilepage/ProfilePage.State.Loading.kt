package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.wear.compose.material.CircularProgressIndicator
import com.google.android.horologist.compose.layout.fillMaxRectangle

@Composable
fun ProfilePageLoadingState() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxRectangle()
    ) {
        CircularProgressIndicator()
    }
}