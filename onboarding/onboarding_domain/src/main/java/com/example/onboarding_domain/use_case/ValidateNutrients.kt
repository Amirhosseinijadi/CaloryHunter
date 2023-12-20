package com.example.onboarding_domain.use_case

import com.example.core.utill.UiText

class ValidateNutrients {

    operator fun invoke(
         carbsRatioText:String,
         proteinRatioText:String,
         fatsRatioText:String

    ):Result{
        val carbsRatio = carbsRatioText.toIntOrNull()
        val proteinRatio = proteinRatioText.toIntOrNull()
        val fatsRatio = fatsRatioText.toIntOrNull()
        if (carbsRatio == null || proteinRatio == null || fatsRatio == null){
           return Result.Error(message = UiText.StringResource(com.example.core.R.string.error_invalid_values))
        }
        if (carbsRatio + proteinRatio + fatsRatio != 100){
            return Result.Error(message = UiText.StringResource(com.example.core.R.string.error_not_100_percent))
        }
        return Result.Success(carbsRatio /100f
            ,proteinRatio /100f
            ,fatsRatio/100f)
    }
    sealed class Result {
        data class Success(
            val carbsRatio: Float
            , val proteinRatio: Float
            , val fatRatio: Float
        ):Result()
        data class Error (val message: UiText): Result ()
    }
}