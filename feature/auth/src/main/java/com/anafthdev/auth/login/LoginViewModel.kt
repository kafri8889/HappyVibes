package com.anafthdev.auth.login

import androidx.lifecycle.SavedStateHandle
import com.anafthdev.foundation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): BaseViewModel<LoginState>(
    savedStateHandle = savedStateHandle,
    defaultState = LoginState()
) {

    fun setEmail(email: String) {
        updateState {
            copy(
                email = email
            )
        }
    }

    fun setPassword(password: String) {
        updateState {
            copy(
                password = password
            )
        }
    }

    fun setShowPassword(showPassword: Boolean) {
        updateState {
            copy(
                showPassword = showPassword
            )
        }
    }

    fun setRememberMe(rememberMe: Boolean) {
        updateState {
            copy(
                rememberMe = rememberMe
            )
        }
    }

}