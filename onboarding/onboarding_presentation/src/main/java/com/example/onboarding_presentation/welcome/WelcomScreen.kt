package com.example.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core_ui.localSpacing
import com.example.core.R
import com.example.core.navigation.Route
import com.example.core.utill.UiEvent
import com.example.onboarding_presentation.component.ActionButton


@Composable
fun WelcomScreen(
    onNavigate : (UiEvent.Navigate) -> Unit
){
    val spacing = localSpacing.current
    val context = LocalContext.current
  
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text (text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(16.dp)  )
        ActionButton(text = stringResource(id = R.string.next), onClick = {onNavigate(UiEvent.Navigate(Route.GENDER))},
            modifier = Modifier.align(Alignment.CenterHorizontally ))



    }

}