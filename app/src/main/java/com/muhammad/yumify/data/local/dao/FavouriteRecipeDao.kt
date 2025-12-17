package com.muhammad.yumify.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.muhammad.yumify.data.local.entity.FavouriteRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao

interface FavouriteRecipeDao {
    @Insert
    suspend fun insertFavouriteRecipe(recipeEntity: FavouriteRecipeEntity)

    @Query("DELETE FROM FavouriteRecipeEntity WHERE id=:id")
    suspend fun deleteFavouriteRecipe(id: String)

    @Query("SELECT * FROM FavouriteRecipeEntity ORDER BY timeStamp DESC")
    fun getFavouriteRecipes(): Flow<List<FavouriteRecipeEntity>>

    @Query("SELECT id FROM FavouriteRecipeEntity")
    fun getFavouriteRecipeIds(): Flow<List<String>>

    @Query("SELECT * FROM FavouriteRecipeEntity WHERE id = :id")
     fun getFavouriteRecipe(id: String): Flow<FavouriteRecipeEntity>?

}