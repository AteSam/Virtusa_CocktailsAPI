package com.atessam.mycocktails.data.remote

import com.atessam.mycocktails.core.Resource
import com.atessam.mycocktails.data.model.Cocktail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class NetworkDataSource
@Inject
constructor(
    private val webService: WebService
){

    suspend fun getCocktailByName(cocktailName: String): Flow<Resource<List<Cocktail>>> =
        callbackFlow {
            offer(
                Resource.Success(webService.getCocktailByName(cocktailName)?.cocktailList?: listOf())

                )

            awaitClose { close() }
        }
}