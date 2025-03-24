package com.studiolaum.sunflowerclone.network

import com.studiolaum.sunflowerclone.BuildConfig
import com.studiolaum.sunflowerclone.data.UnsplashPhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface UnsplashApi {
    @GET("search/photos")
    suspend fun searchPhoto(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 1,
        @Query("client_id") clientId: String = BuildConfig.ACCESS_KEY,
    ): UnsplashPhotoResponse
}
