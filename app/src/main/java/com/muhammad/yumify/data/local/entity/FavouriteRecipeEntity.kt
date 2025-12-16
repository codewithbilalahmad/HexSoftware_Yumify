package com.muhammad.yumify.data.local.entity

import androidx.room.*
import com.muhammad.yumify.domain.model.RecipeIngredient

@Entity
data class FavouriteRecipeEntity(
    @PrimaryKey val id: String,
    val name : String,
    val imageUrl : String,
    val area : String,
    val timeStamp : Long = System.currentTimeMillis(),
    val category : String,
    val instructions : String,
    val source : String,
    val tags : String,
    val youtubeLink : String,
    val ingredients: List<RecipeIngredient>
)
