package com.rizfan.moviews.favorite.di

import android.content.Context
import com.rizfan.moviews.di.FavoriteModuleDependencies
import com.rizfan.moviews.favorite.ui.FavoriteActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependencies::class]
)
interface FavoriteComponent {

    fun inject(activity: FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}