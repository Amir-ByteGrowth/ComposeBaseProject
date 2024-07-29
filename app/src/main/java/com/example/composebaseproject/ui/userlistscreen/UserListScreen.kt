package com.example.composebaseproject.ui.userlistscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composebaseproject.data.remote.Resource


@Composable
fun UserListScreen(
    modifier: Modifier = Modifier,
    userListViewModel: UserListViewModel = hiltViewModel(),
) {
    val userListResponse = userListViewModel.uiState.collectAsState().value

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        when (userListResponse) {
            is Resource.Error -> {
                Text(text = userListResponse.responseError?.message ?: "Eroor")
            }

            is Resource.Initial -> {}
            is Resource.Loading -> {
                Text(
                    text = "Loading",
                    style = MaterialTheme.typography.headlineLarge.copy(color = Color.Black)
                )
            }

            is Resource.Success -> {
                userListResponse.data.data?.let {
                    LazyColumn {
                        items(it) { item ->
                            Text(
                                text = item.id + "    " + item.name,
                                modifier = modifier.padding(5.dp),
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }

            }
        }
    }
}