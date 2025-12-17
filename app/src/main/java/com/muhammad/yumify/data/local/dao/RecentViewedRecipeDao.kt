package com.muhammad.yumify.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.muhammad.yumify.data.local.entity.RecentViewedRecipeEntity
import com.muhammad.yumify.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecentViewedRecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentViewedRecipe(recentViewedRecipeEntity: RecentViewedRecipeEntity)

    @Query("DELETE FROM RecentViewedRecipeEntity WHERE id = :id")
    suspend fun deleteRecentViewedRecipe(id: String)

    @Query("SELECT * FROM RecentViewedRecipeEntity WHERE id=:id")
    fun getRecentViewedRecipe(id: String): Flow<RecentViewedRecipeEntity>?

    @Query("SELECT * FROM RecentViewedRecipeEntity ORDER BY timeStamp DESC")
    fun getRecentViewedRecipes(): Flow<List<RecentViewedRecipeEntity>>
}