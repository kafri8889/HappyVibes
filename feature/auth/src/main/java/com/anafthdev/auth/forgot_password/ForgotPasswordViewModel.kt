package com.anafthdev.auth.forgot_password

import androidx.lifecycle.SavedStateHandle
import com.anafthdev.foundation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
): BaseViewModel<ForgotPasswordState>(
    defaultState = ForgotPasswordState(),
    savedStateHandle = savedStateHandle
) {

    fun setEmail(email: String) {
        updateState {
            copy(
                email = email
            )
        }
    }

}