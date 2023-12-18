package com.example.caloryhunter.navigation

import androidx.navigation.NavController
import com.example.core.utill.UiEvent

fun NavController.navigate(event:UiEvent.Navigate){
  this.navigate(event.route)
}