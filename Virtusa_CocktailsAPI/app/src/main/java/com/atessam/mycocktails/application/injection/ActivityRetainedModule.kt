package com.atessam.mycocktails.application.injection

import com.atessam.mycocktails.domain.CocktailRepository
import com.atessam.mycocktails.domain.MyCocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ActivityRetainedModule {

    @Binds
    abstract fun dataSource(imp: MyCocktailRepository):CocktailRepository
}