package kr.co.onboard.address

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kr.co.ui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class InputAddressViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): BaseViewModel<InputAddressViewModel.State>(savedStateHandle) {

    private val _showCropScreen = MutableSharedFlow<Unit>()
    val showCropScreen = _showCropScreen.asSharedFlow()

    fun onCoordinateChanged(latitude: Double, longitude: Double) = updateState {
        copy(
            latitude = latitude,
            longitude = longitude
        )
    }

    fun updateAddresses(fullRoadAddress: String, bCode: String) {
        updateState {
            copy(fullRoadAddress = fullRoadAddress, bCode = bCode)
        }
    }

    fun saveAddress(){

    }

    fun loadAddress(): State {
        Timber.d("state.value: ${state.value}")
        return state.value
    }

    data class State(
        val fullRoadAddress: String? = null,
        val bCode: String? = null,
        val latitude: Double? = null,
        val longitude: Double? = null,
    ): BaseViewModel.State

    override fun createInitialState(savedState: Parcelable?): State {
        return State()
    }
}