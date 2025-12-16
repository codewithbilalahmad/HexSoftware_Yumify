package com.muhammad.yumify.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable
import kotlin.random.Random

@Immutable
@Serializable
data class Recipe(
    val id : String,
    val name : String,
    val imageUrl : String,
    val area : String,
    val category : String,
    val instructions : String,
    val source : String,
    val tags : String,
    val isFavourite : Boolean = false,
    val youtubeLink : String,
    val ingredients: List<RecipeIngredient>,
    val time : Int = listOf(10, 20, 30,40,50).random(),
    val calories : Int = Random.nextInt(250, 550)
)
