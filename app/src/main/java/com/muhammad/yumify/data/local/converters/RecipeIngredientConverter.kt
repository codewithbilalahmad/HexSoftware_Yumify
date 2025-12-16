package com.muhammad.yumify.data.local.converters

import androidx.room.TypeConverter
import com.muhammad.yumify.domain.model.RecipeIngredient
import kotlinx.serialization.json.*

class RecipeIngredientConverter{
    private val json = Json {
        ignoreUnknownKeys = true
    }
    @TypeConverter
    fun fromIngredientList(value : List<RecipeIngredient>) : String{
        return Json.encodeToString(value)
    }
    @TypeConverter
    fun toIngredientList(value : String) : List<RecipeIngredient> {
        return json.decodeFromString(value)
    }
}