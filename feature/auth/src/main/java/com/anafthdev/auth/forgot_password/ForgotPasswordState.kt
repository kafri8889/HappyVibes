package com.anafthdev.auth.forgot_password

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class ForgotPasswordState(
    val email: String = ""
): Parcelable
