package com.muhammad.yumify.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muhammad.yumify.data.local.converters.RecipeIngredientConverter
import com.muhammad.yumify.data.local.dao.FavouriteRecipeDao
import com.muhammad.yumify.data.local.dao.RecentViewedRecipeDao
import com.muhammad.yumify.data.local.entity.FavouriteRecipeEntity
import com.muhammad.yumify.data.local.entity.RecentViewedRecipeEntity

@Database(
    entities = [FavouriteRecipeEntity::class, RecentViewedRecipeEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    RecipeIngredientConverter::class
)
abstract class YumifyDatabase : RoomDatabase(){
    abstract fun favouriteRecipeDao() : FavouriteRecipeDao
    abstract fun recentViewedRecipeDao() : RecentViewedRecipeDao
}
