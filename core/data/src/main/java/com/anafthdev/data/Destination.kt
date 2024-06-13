package com.anafthdev.data

import kotlinx.serialization.Serializable

@Serializable
sealed class Destination {

    @Serializable
    data object Onboarding: Destination()

    @Serializable
    data object Register: Destination()

    @Serializable
    data object Login: Destination()

    @Serializable
    data object ForgotPassword: Destination()

}