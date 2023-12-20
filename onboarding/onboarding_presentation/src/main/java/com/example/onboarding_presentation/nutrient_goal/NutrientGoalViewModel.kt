package com.example.onboarding_presentation.nutrient_goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.prefences.Prefences
import com.example.core.domain.use_case.FilterOutDigits
import com.example.core.navigation.Route
import com.example.core.utill.UiEvent
import com.example.onboarding_domain.use_case.ValidateNutrients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val prefrences: Prefences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
):ViewModel() {
    var state by mutableStateOf(NutrientGoalState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event:NutrientGoalEvent){
        when(event){
            is NutrientGoalEvent.OnCarbsRatioEnter -> {
               state =  state.copy(carbsRatio = filterOutDigits(event.ratio))

            }
            is NutrientGoalEvent.OnProteinRatioEnter -> {
                state =  state.copy(proteiinRatio = filterOutDigits(event.ratio))

            }

            is NutrientGoalEvent.OnFatsRatioEnter -> {
                state =  state.copy(fatRatio = filterOutDigits(event.ratio))
            }

            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    carbsRatioText = state.carbsRatio,
                    proteinRatioText = state.proteiinRatio,
                    fatsRatioText = state.fatRatio
                )
                when(result){
                    is ValidateNutrients.Result.Success ->{
                        prefrences.saveCarbRatio(result.carbsRatio)
                        prefrences.saveProteinRation(result.proteinRatio)
                        prefrences.saveFatRatio(result.fatRatio)

                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.Navigate(Route.TRACKER_OVERVIEW))
                        }
                    }
                    is ValidateNutrients.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackBar(result.message))
                        }
                    }
                }
            }
        }
    }
}