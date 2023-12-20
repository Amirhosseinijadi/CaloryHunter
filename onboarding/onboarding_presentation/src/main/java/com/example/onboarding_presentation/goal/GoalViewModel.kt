package com.example.onboarding_presentation.goal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.GoalType
import com.example.core.domain.prefences.Prefences
import com.example.core.navigation.Route
import com.example.core.utill.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalViewModel @Inject constructor(
    private val prefrences : Prefences
):ViewModel() {
    var selectedGoalType by mutableStateOf<GoalType>(
        GoalType.KeepWeight
    )
    private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onGoalTypeSelect(goalType: GoalType) {
        selectedGoalType = goalType
    }

    fun onNextClicked(){
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Navigate(Route.NUTRIENT_GOAL))
            prefrences.saveGoalType(selectedGoalType)
        }
    }

}