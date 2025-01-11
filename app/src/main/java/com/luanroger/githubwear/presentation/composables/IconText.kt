package com.luanroger.githubwear.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text

@Composable
fun IconText(icon: @Composable () -> Unit, customText: (@Composable () -> Unit)? = null,
             text: String? = null, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon()
        customText?.let {
            it()
        }
        text?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.body1
            )
        }
    }
}