package com.luanroger.githubwear.presentation.profilepage

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.wear.compose.foundation.rememberActiveFocusRequester
import androidx.wear.compose.foundation.rotary.RotaryScrollableDefaults.behavior
import androidx.wear.compose.foundation.rotary.rotaryScrollable
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.luanroger.githubwear.R
import com.luanroger.githubwear.presentation.composables.IconText

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfilePageSuccessState(uiState: ProfilePageState.Success,
                            scrollState: ScrollState) {
    val iconsModifier = Modifier.size(12.dp)

    val focusRequester = rememberActiveFocusRequester()
    val userInfo = uiState.user

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
            .padding(16.dp)
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            GlideImage(
                model = userInfo.avatarUrl,
                contentDescription = "Description",
                modifier = Modifier
                    .width(100.dp)
                    .clip(
                        RoundedCornerShape(100)
                    ),
                loading = placeholder(R.drawable.squirrel)
            )
            Text(
                userInfo.login,
                style = MaterialTheme.typography.title2.copy(fontWeight = FontWeight.Bold)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if(userInfo.bio != null) {
                    Text(
                        "\"${userInfo.bio}\"",
                        style = MaterialTheme.typography.caption2.copy(fontStyle = FontStyle.Italic),
                    )
                }
                IconText(
                    customText = {
                        Text(
                            buildAnnotatedString {
                                append(userInfo.followers.toString())
                                withStyle(style = SpanStyle(color = Color.Gray)) {
                                    append(" followers • ")
                                }
                                append(userInfo.following.toString())
                                withStyle(style = SpanStyle(color = Color.Gray)) {
                                    append(" following")
                                }
                            },
                            style = MaterialTheme.typography.caption3
                        )
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.people),
                            contentDescription = "Followers",
                            modifier = iconsModifier
                        )
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                IconText(
                    text = if(userInfo.location.isNullOrEmpty()) "No location" else userInfo.location,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "Location",
                            modifier = iconsModifier
                        )
                    }
                )
                IconText(
                    text = if(userInfo.blog.isNullOrEmpty()) "No blog" else userInfo.blog,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "Blog",
                            modifier = iconsModifier
                        )
                    }
                )
                IconText(
                    text = if(userInfo.company.isNullOrEmpty()) "No company" else userInfo.company,
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.organization),
                            contentDescription = "Company",
                            modifier = iconsModifier
                        )
                    }
                )
            }
        }

    }

}