package com.anafthdev.happyvibes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anafthdev.happyvibes.data.Destinations
import com.anafthdev.happyvibes.theme.HappyVibesTheme
import com.anafthdev.onboarding.OnboardingScreen

@Composable
fun HappyVibes(modifier: Modifier = Modifier) {
    HappyVibesTheme {
        Scaffold(modifier = modifier) { innerPadding ->
            MainNavigation(
                modifier = Modifier
                    .padding(innerPadding)
            )
        }
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
