package com.atessam.mycocktails.data.remote

import com.atessam.mycocktails.data.model.CocktailList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET("search.php")
    suspend fun getCocktailByName(@Query(value = "s") aName: String): CocktailList?
}