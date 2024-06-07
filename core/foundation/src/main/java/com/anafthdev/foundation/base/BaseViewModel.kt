package com.anafthdev.foundation.base

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.anafthdev.foundation.base.BaseViewModel.OnStateUpdateListener
import kotlinx.coroutines.flow.StateFlow

/**
 *  kelas dasar (base class) untuk view model.
 *  Ini berarti kelas ini memberikan kerangka dasar untuk view model
 *  yang akan diturunkan (derived) oleh kelas-kelas lain.
 *
 *  @param savedStateHandle savedStateHandle yang digunakan untuk menyimpan state
 *  @param defaultState default state
 *
 *  @author kafri8889
 */
abstract class BaseViewModel<STATE: Parcelable>(
	private val savedStateHandle: SavedStateHandle,
	private val defaultState: STATE
): ViewModel() {

	/**
	 * Key yang digunakan untuk menyimpan dan mengambil state di savedStateHandle
	 */
	private val KEY_STATE = "state"

	private var onStateUpdateListener: OnStateUpdateListener<STATE> = OnStateUpdateListener { _, _ -> }

	val state: StateFlow<STATE> = savedStateHandle.getStateFlow(KEY_STATE, defaultState)

	/**
	 * Function yang digunakan untuk memperbarui state dari [savedStateHandle]
	 *
	 * @param triggerStateChange invoke [OnStateUpdateListener.onStateUpdated] if `true`, `false` otherwise
	 * @param newState parameter ini akan memberikan state saat ini sebagai `this`.
	 */
	protected fun updateState(triggerStateChange: Boolean = true, newState: STATE.() -> STATE) {
		// get current state
		savedStateHandle.get<STATE>(KEY_STATE)?.let { state ->
			// simpan state baru ke savedStateHandle
			val mNewState = newState(state)
			savedStateHandle[KEY_STATE] = mNewState
			if (triggerStateChange) onStateUpdateListener.onStateUpdated(state, mNewState)
		}
	}

	fun addOnStateUpdateListener(listener: OnStateUpdateListener<STATE>) {
		onStateUpdateListener = listener
	}

	fun interface OnStateUpdateListener<STATE> {
		fun onStateUpdated(oldState: STATE, newState: STATE)
	}

}