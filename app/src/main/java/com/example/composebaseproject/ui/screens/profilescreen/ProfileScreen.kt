package com.example.composebaseproject.ui.screens.profilescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composebaseproject.ui.navigations.Destination


@Composable
fun ProfileScreen(
    profile: Destination.Profile,
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "id  ${profile.id}    name    ${profile.name}")
            Spacer(modifier = modifier.height(15.dp))
            Button(onClick = navigate) {
                Text(text = "Navigate to UserList")
            }
        }
    }
}