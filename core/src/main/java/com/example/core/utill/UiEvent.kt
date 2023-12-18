package com.example.core.utill

sealed class UiEvent{
    data class Navigate(val route:String):UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackBar(val massage:UiText):UiEvent()
}
