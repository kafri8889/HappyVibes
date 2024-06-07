package com.anafthdev.happyvibes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anafthdev.auth.authNavigation
import com.anafthdev.data.Destination
import com.anafthdev.onboarding.OnboardingScreen
import com.anafthdev.ui.theme.HappyVibesTheme

@Composable
fun HappyVibes(modifier: Modifier = Modifier) {
    HappyVibesTheme {
        MainNavigation(modifier)
    }
}

@Composable
private fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    val navigateTo: (Destination, builder: (NavOptionsBuilder.() -> Unit)?) -> Unit = { destination, options ->
        if (options != null) navController.navigate(destination, options)
        else navController.navigate(destination)
    }

    NavHost(
        navController = navController,
        startDestination = Destination.Onboarding,
        modifier = modifier
    ) {
        authNavigation(
            navigateTo = navigateTo
        )

        composable<Destination.Onboarding> {
            OnboardingScreen(
                navigateTo = navigateTo
            )
        }
    }
}
