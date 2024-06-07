package com.anafthdev.auth.register

import androidx.lifecycle.SavedStateHandle
import com.anafthdev.foundation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): BaseViewModel<RegisterState>(
    defaultState = RegisterState(),
    savedStateHandle = savedStateHandle
) {

    fun setName(name: String) {
        updateState {
            copy(
                name = name
            )
        }
    }

    fun setUsername(username: String) {
        updateState {
            copy(
                username = username
            )
        }
    }

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

    fun setConfirmationPassword(confirmPassword: String) {
        updateState {
            copy(
                confirmationPassword = confirmPassword
            )
        }
    }

    fun setPasswordVisibility(show: Boolean) {
        updateState {
            copy(
                showPassword = show
            )
        }
    }

    fun setConfirmationPasswordVisibility(show: Boolean) {
        updateState {
            copy(
                showConfirmationPassword = show
            )
        }
    }

}