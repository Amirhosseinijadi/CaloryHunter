package com.example.onboarding_presentation.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.R
import com.example.core.domain.model.ActivityLevel
import com.example.core.utill.UiEvent
import com.example.core_ui.localSpacing
import com.example.onboarding_presentation.component.ActionButton
import com.example.onboarding_presentation.component.SelectableButton

@Composable
fun ActivityScreen(
    viewmodel:ActivityViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
){
    val spacing = localSpacing.current
     LaunchedEffect(key1 = true)   {
         viewmodel.uiEvent.collect {event ->
             when(event){
                 is UiEvent.Navigate -> {onNavigate(event)}
                 else -> Unit
             }
         }
     }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3
            )

            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.low),
                    isSelected = viewmodel.selectedActivityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewmodel.onActivityLevelSelect(ActivityLevel.Low) },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = viewmodel.selectedActivityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewmodel.onActivityLevelSelect(ActivityLevel.Medium) },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )

                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = viewmodel.selectedActivityLevel is ActivityLevel.High,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewmodel.onActivityLevelSelect(ActivityLevel.High) },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
            }

            ActionButton(
                text = stringResource(id = R.string.next),
                onClick = viewmodel::onNextClicked,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

        }
    }






