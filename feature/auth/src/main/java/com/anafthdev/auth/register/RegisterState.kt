package com.anafthdev.auth.register

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class RegisterState(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmationPassword: String = "",
    val showPassword: Boolean = false,
    val showConfirmationPassword: Boolean = false
): Parcelable
