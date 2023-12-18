package com.example.onboarding_presentation.height

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core.utill.UiEvent
import com.example.core_ui.localSpacing
import com.example.core.R
import com.example.onboarding_presentation.component.ActionButton
import com.example.onboarding_presentation.component.UnitTextField
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HeightScreen(
    scaffoldState: ScaffoldState,
    onNavigate : (UiEvent.Navigate) -> Unit,
    viewModel: HeightViewModel = hiltViewModel()
){
    val spacing = localSpacing.current
    val context = LocalContext.current
   LaunchedEffect(key1 =true){
       viewModel.uiEvent.collectLatest { event ->
           when(event){
               is UiEvent.Navigate ->{onNavigate(event)}
               is UiEvent.ShowSnackBar -> {scaffoldState.snackbarHostState.showSnackbar(event.massage.asString(context))}
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
            Text(
                text = stringResource(id = R.string.whats_your_height),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = viewModel.height,
                onValueChanged = viewModel::onHeightEnter,
                unit = stringResource(id = R.string.cm)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClicked,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}