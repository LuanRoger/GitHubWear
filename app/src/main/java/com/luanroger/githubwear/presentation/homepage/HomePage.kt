package com.luanroger.githubwear.presentation.homepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.google.android.horologist.compose.layout.ScreenScaffold
import com.luanroger.githubwear.R

@Composable
fun HomePage(navigateToProfile: (username: String) -> Unit) {
    val scrollState = rememberScrollState()

    ScreenScaffold(scrollState = scrollState) {
        var currentName by remember { mutableStateOf("LuanRoger") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo_github),
                contentDescription = "GitHub logo",
            )
            Spacer(modifier = Modifier.height(16.dp))
            BasicTextField(
                value = currentName,
                onValueChange = { currentName = it },
            ) {
                if(currentName.isEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search icon"
                    )
                }
                else {
                    Text(
                        text = "Olá, $currentName!",
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if(currentName.isNotEmpty()) {
                Row {
                    Button(onClick = { navigateToProfile(currentName) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.check),
                            contentDescription = "Profile icon"
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Button(onClick = { currentName = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.x),
                            contentDescription = "Profile icon"
                        )
                    }
                }
            }
            else {
                Text(
                    text = "Digite um nome de usuário.",
                    style = MaterialTheme.typography.caption3
                )
            }
        }
    }
}