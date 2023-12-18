package com.example.core.data.prefences

import android.content.SharedPreferences
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.prefences.Prefences

class DefaultPreferences(
    val sharedPref:SharedPreferences
):Prefences {
    override fun saveGender(gender: Gender) {
        sharedPref.edit()
            .putString(Prefences.KEY_GENDER,gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPref.edit()
            .putInt(Prefences.KEY_AGE,age)
            .apply()

    }

    override fun saveWeight(weight: Float) {
        sharedPref.edit()
            .putFloat(Prefences.KEY_WEIGHT,weight)
            .apply()

    }

    override fun saveHeight(height: Int) {
        sharedPref.edit()
            .putInt(Prefences.KEY_HEIGHT,height)
            .apply()


    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPref.edit()
            .putString(Prefences.KEY_ACTIVITY_LEVEL,level.name)
            .apply()

    }

    override fun saveGoalType(type: GoalType) {
      sharedPref.edit()
          .putString(Prefences.KEY_GOAL_TYPE,type.name)
          .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Prefences.KEY_CARB_RATIO,ratio)
            .apply()

    }

    override fun saveProteinRation(ratio: Float) {
        sharedPref.edit()
            .putFloat(Prefences.KEY_PROTEIN_RATIO,ratio)
            .apply()

    }

    override fun saveFatRatio(ratio: Float) {
        sharedPref.edit()
            .putFloat(Prefences.KEY_FAT_RATIO,ratio)
            .apply()


    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPref.getInt(Prefences.KEY_AGE,-1)
        val height = sharedPref.getInt(Prefences.KEY_HEIGHT,-1)
        val weight = sharedPref.getFloat(Prefences.KEY_WEIGHT,-1f)
        val genderString = sharedPref.getString(Prefences.KEY_GENDER,null)
        val activityLevelString = sharedPref.getString(Prefences.KEY_ACTIVITY_LEVEL,null)
        val goalTypeString = sharedPref.getString(Prefences.KEY_GOAL_TYPE,null)
        val carbRatio = sharedPref.getFloat(Prefences.KEY_CARB_RATIO,-1f)
        val proteinRatio = sharedPref.getFloat(Prefences.KEY_PROTEIN_RATIO,-1f)
        val fatRatio = sharedPref.getFloat(Prefences.KEY_FAT_RATIO,-1f)

        return UserInfo(
            gender = Gender.fromString(genderString ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevelString ?: "medium"),
            goalType = GoalType.fromString(goalTypeString ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRatio = proteinRatio,
            fatRatio = fatRatio
        )
    }

    override fun saveShouldShowOnBoarding(shouldShow: Boolean) {

    }

    override fun loadShouldShowOnBoarding(): Boolean {
        return true

    }
}