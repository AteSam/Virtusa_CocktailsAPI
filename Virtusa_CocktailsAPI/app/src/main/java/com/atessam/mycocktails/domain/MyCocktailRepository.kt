package com.atessam.mycocktails.domain

import androidx.lifecycle.LiveData
import com.atessam.mycocktails.core.Resource
import com.atessam.mycocktails.data.LocalDataSource
import com.atessam.mycocktails.data.model.Cocktail
import com.atessam.mycocktails.data.model.CocktailEntity
import com.atessam.mycocktails.data.model.asCocktailEntity
import com.atessam.mycocktails.data.remote.NetworkDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ActivityRetainedScoped
class MyCocktailRepository
@Inject
constructor(
        private val networkDataSource: NetworkDataSource,
        private val localDataSource: LocalDataSource
):CocktailRepository{
        override suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>> =
                callbackFlow {

                        offer(getCachedCocktails(cocktailName))

                        networkDataSource.getCocktailByName(cocktailName).collect {
                                when(it){
                                         is Resource.Success->{
                                                for (cocktail in it.data){
                                                        saveCocktail(cocktail.asCocktailEntity())
                                                }
                                                 offer(getCachedCocktails(cocktailName))
                                         }
                                        is Resource.Failure->{
                                                offer(getCachedCocktails(cocktailName))
                                        }
                                }
                        }


                        awaitClose { cancel() }
                }

    override suspend fun saveFavoriteCocktail(cocktail: Cocktail) {
        localDataSource.saveFavoriteCocktail(cocktail)
    }



        override suspend fun isCocktailFavorite(cocktail: Cocktail): Boolean =
                localDataSource.isCocktailFavorite(cocktail)

        override suspend fun saveCocktail(cocktail: CocktailEntity) {
                localDataSource.saveCocktail(cocktail)
        }

        override fun getFavoritesCocktails(): LiveData<List<Cocktail>> {
                return localDataSource.getFavoritesCocktails()
        }

        override suspend fun deleteFavoriteCocktail(cocktail: Cocktail) {
                localDataSource.deleteCocktail(cocktail)
        }

        override suspend fun getCachedCocktails(cocktailName: String): Resource<List<Cocktail>> {
                return localDataSource.getCachedCocktails(cocktailName)
        }
}