package com.anafthdev.auth.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginState(
    val email: String = "",
    val password: String = "",
    val rememberMe: Boolean = false,
    val showPassword: Boolean = false,
): Parcelable
