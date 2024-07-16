package com.shimul.jokes_for_me.data.remote

import com.shimul.jokes_for_me.data.ResponseJokes
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JokesApiService {

    @GET("joke/Any")
    suspend fun getJokes(): Response<ResponseJokes>
}

object JokeClient{

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jokesApiService = retrofit.create(JokesApiService::class.java)

}