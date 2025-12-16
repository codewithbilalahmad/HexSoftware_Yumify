package com.muhammad.yumify.di

import androidx.room.Room
import com.muhammad.yumify.YumifyApplication
import com.muhammad.yumify.data.local.YumifyDatabase
import com.muhammad.yumify.data.local.repository.FavouriteRecipeRepositoryImp
import com.muhammad.yumify.data.local.repository.RecentViewedRecipeRepositoryImp
import com.muhammad.yumify.data.remote.network.RecipeNetworkImp
import com.muhammad.yumify.data.remote.network.client.HttpClientFactory
import com.muhammad.yumify.data.remote.repository.RecipeRespositoryImp
import com.muhammad.yumify.domain.network.RecipeNetwork
import com.muhammad.yumify.domain.repository.FavouriteRecipeRepository
import com.muhammad.yumify.domain.repository.RecentViewedRecipeRepository
import com.muhammad.yumify.domain.repository.RecipeRepository
import com.muhammad.yumify.presentation.screens.categories.CategoriesViewModel
import com.muhammad.yumify.presentation.screens.category_recipes.CategoryRecipesViewModel
import com.muhammad.yumify.presentation.screens.home.HomeViewModel
import com.muhammad.yumify.utils.Constants.DATABASE_NAME
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { YumifyApplication.INSTANCE }
    single {
        Room.databaseBuilder(get(), YumifyDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(true)
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<YumifyDatabase>().favouriteRecipeDao() }
    single { get<YumifyDatabase>().recentViewedRecipeDao() }
    singleOf(::FavouriteRecipeRepositoryImp).bind<FavouriteRecipeRepository>()
    singleOf(::RecentViewedRecipeRepositoryImp).bind<RecentViewedRecipeRepository>()
    single { HttpClientFactory.createClient() }
    singleOf(::RecipeNetworkImp).bind<RecipeNetwork>()
    singleOf(::RecipeRespositoryImp).bind<RecipeRepository>()
    viewModelOf(::HomeViewModel)
    viewModelOf(::CategoriesViewModel)
    viewModelOf(::CategoryRecipesViewModel)
}