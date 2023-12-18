package com.example.onboarding_presentation.weight

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.R
import com.example.core.domain.prefences.Prefences
import com.example.core.domain.use_case.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.utill.UiEvent
import com.example.core.utill.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel@Inject constructor(
    private val prefences: Prefences,
    private val filterOutDigits: FilterOutDigits
):ViewModel() {

    var weight by mutableStateOf("80.0")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun onweightEnter(weight:String){
        if (weight.length <= 5){
            this.weight = weight
        }


    }
    fun onNextClicked(){
        viewModelScope.launch {
            val weightNumber = weight.toFloatOrNull() ?: kotlin.run {
                _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(R.string.error_weight_cant_be_empty)
                )
                )
                return@launch
            }


            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
            prefences.saveWeight(weightNumber)

        }

    }


}