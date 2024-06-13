package com.anafthdev.auth

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import com.anafthdev.auth.forgot_password.ForgotPasswordScreen
import com.anafthdev.auth.login.LoginScreen
import com.anafthdev.auth.register.RegisterScreen
import com.anafthdev.data.Destination

fun NavGraphBuilder.authNavigation(
    navController: NavController,
    navigateTo: (Destination, builder: (NavOptionsBuilder.() -> Unit)?) -> Unit
) {
    composable<Destination.Register> { backEntry ->
        RegisterScreen(
            viewModel = hiltViewModel(backEntry),
            navigateTo = navigateTo
        )
    }

    composable<Destination.Login> { backEntry ->
        LoginScreen(
            viewModel = hiltViewModel(backEntry),
            navigateTo = navigateTo
        )
    }

    composable<Destination.ForgotPassword> { backEntry ->
        ForgotPasswordScreen(
            viewModel = hiltViewModel(backEntry),
            navigateTo = navigateTo,
            onNavigationIconClicked = navController::popBackStack
        )
    }
}