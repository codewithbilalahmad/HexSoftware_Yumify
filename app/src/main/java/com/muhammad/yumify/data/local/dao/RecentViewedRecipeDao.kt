package com.muhammad.yumify.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muhammad.yumify.data.local.entity.RecentViewedRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentViewedRecipeDao {
    @Insert
    suspend fun insertRecentViewedRecipe(recentViewedRecipeEntity: RecentViewedRecipeEntity)

    @Query("DELETE FROM RecentViewedRecipeEntity WHERE id = :id")
    suspend fun deleteRecentViewedRecipe(id: String)

    @Query("SELECT * FROM RecentViewedRecipeEntity ORDER BY timeStamp DESC")
    fun getRecentViewedRecipes(): Flow<List<RecentViewedRecipeEntity>>
}