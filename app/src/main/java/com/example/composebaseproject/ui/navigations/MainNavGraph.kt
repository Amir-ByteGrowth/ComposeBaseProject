package com.example.composebaseproject.ui.navigations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.composebaseproject.ui.screens.homescreen.HomeScreen
import com.example.composebaseproject.ui.screens.profilescreen.ProfileScreen
import com.example.composebaseproject.ui.screens.userlistscreen.UserListScreen
import kotlinx.serialization.Serializable


@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {


    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Home) {
        composable<Destination.Home> {
            HomeScreen {
                navController.navigate(Destination.Profile("amir", 2))
            }
        }
        composable<Destination.Profile> { backStackEntry ->
            val profile = backStackEntry.toRoute<Destination.Profile>()
            ProfileScreen(profile) {
                navController.navigate(Destination.UserList)
            }
        }

        composable<Destination.UserList> {
            UserListScreen()
        }
    }

}


sealed class Destination {
    @Serializable
    object Home

    @Serializable
    data class Profile(val name: String, val id: Int)

    @Serializable
    object UserList
}