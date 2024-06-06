package com.anafthdev.happyvibes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anafthdev.happyvibes.data.Destinations
import com.anafthdev.onboarding.OnboardingScreen
import com.anafthdev.ui.theme.HappyVibesTheme

@Composable
fun HappyVibes(modifier: Modifier = Modifier) {
    HappyVibesTheme {
        MainNavigation()
    }
}

@Composable
private fun MainNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.Onboarding,
        modifier = modifier
    ) {
        composable<Destinations.Onboarding> { backEntry ->
            OnboardingScreen()
        }
    }
}
