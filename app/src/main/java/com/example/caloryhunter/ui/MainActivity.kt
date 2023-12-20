package com.example.caloryhunter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.caloryhunter.navigation.navigate
import com.example.caloryhunter.ui.theme.CaloryTrackerTheme
import com.example.core.navigation.Route
import com.example.onboarding_presentation.activity.ActivityScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navcontroller = rememberNavController()
                val scafoldState = rememberScaffoldState()
                Scaffold(
                    scaffoldState = scafoldState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navhost = NavHost(
                        navController = navcontroller,
                        startDestination = Route.WELCOME,
                        modifier = Modifier.padding(it)
                    ) {
                        composable(Route.WELCOME) {
                            WelcomScreen(onNavigate = navcontroller::navigate)
                        }
                        composable(Route.AGE) {

                            AgeScreen(
                                scafoldState = scafoldState,
                                onNavigate = navcontroller::navigate
                            )
                        }
                        composable(Route.GENDER) {
                            GenderScreen(onNavigate = navcontroller::navigate)
                        }
                        composable(Route.HEIGHT) {
                            HeightScreen(scaffoldState = scafoldState, onNavigate = navcontroller::navigate)
                        }
                        composable(Route.WEIGHT) {
                          WeightScreen(scaffoldState = scafoldState, onNavigate = navcontroller::navigate)
                        }
                        composable(Route.ACTIVITY) {
                            ActivityScreen(onNavigate = navcontroller::navigate )
                        }
                        composable(Route.GOAL) {
                          GoalScreen(onNavigate = navcontroller::navigate)
                        }
                        composable(Route.NUTRIENT_GOAL) {
                          NutrientGoalScreen(scaffoldState = scafoldState, onNavigate = navcontroller::navigate)
                        }


                        composable(Route.TRACKER_OVERVIEW) {

                        }
                        composable(Route.SEARCH) {

                        }

                    }
                }
            }
        }
    }
}
