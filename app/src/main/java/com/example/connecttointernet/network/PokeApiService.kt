package com.example.connecttointernet.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val URL_API = "https://pokeapi.co/api/v2/"
private val retrofit = Retrofit.Builder()
    //Usamos GSON por comodida, para que no nos devuelva un String gigante
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(URL_API)
    .build()

interface PokeApiService {
    @GET("pokemon/{id}")
   suspend fun getPokemon(@Path("id")id: Int): PokeData

}

object PokeApi {
    val retrofitService : PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}