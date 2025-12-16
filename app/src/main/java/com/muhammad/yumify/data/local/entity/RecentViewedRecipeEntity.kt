package com.muhammad.yumify.data.local.entity

import androidx.room.*
import com.muhammad.yumify.domain.model.RecipeIngredient

@Entity
data class RecentViewedRecipeEntity(
    @PrimaryKey val id: String,
    val name : String,
    val imageUrl : String,
    val area : String,
    val category : String,
    val instructions : String,
    val source : String,
    val timeStamp : Long,
    val isFavourite : Boolean,
    val tags : String,
    val youtubeLink : String,
    val ingredients: List<RecipeIngredient>,
    val calories : Int,
    val time : Int
)
