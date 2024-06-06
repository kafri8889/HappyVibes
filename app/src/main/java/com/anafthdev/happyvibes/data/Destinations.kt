package com.anafthdev.happyvibes.data

import kotlinx.serialization.Serializable

@Serializable
sealed class Destinations {

    @Serializable
    data object Onboarding: Destinations()

}
