package com.studiolaum.sunflowerclone.network

import com.studiolaum.sunflowerclone.BuildConfig
import com.studiolaum.sunflowerclone.data.UnsplashPhotoResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.unsplash.com/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface UnsplashApis {
    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 1,
        @Query("client_id") clientId: String = BuildConfig.ACCESS_KEY,
    ): UnsplashPhotoResponse
}

object UnsplashApi {
    val unsplashService by lazy { retrofit.create(UnsplashApis::class.java) }
}
