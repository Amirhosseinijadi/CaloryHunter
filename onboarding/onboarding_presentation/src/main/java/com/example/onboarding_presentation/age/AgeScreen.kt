package com.example.onboarding_presentation.age

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.utill.UiEvent
import com.example.core_ui.localSpacing
import com.example.onboarding_presentation.component.ActionButton
import com.example.onboarding_presentation.component.UnitTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AgeScreen(
    scafoldState:ScaffoldState,
    onNavigate : (UiEvent.Navigate) -> Unit,
    viewModel:AgeViewModel = hiltViewModel(),
){
    val spacing = localSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collectLatest { event ->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar ->{
                 scafoldState.snackbarHostState.showSnackbar(
                     message = event.massage.asString(context)
                 )
                }
                else -> Unit
            }
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.whats_your_age))

            Spacer(modifier = Modifier.height(spacing.spaceMedium))

            UnitTextField(value = viewModel.age, onValueChanged = viewModel::onAgeEnter, unit = stringResource(
                id = R.string.years
            ))

        }
        ActionButton(text = stringResource(id = R.string.next), onClick =  viewModel::onNextClicked, modifier = Modifier.align(
            Alignment.BottomEnd))

    }
}