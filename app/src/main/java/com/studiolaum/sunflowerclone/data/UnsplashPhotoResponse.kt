package com.studiolaum.sunflowerclone.data


data class UnsplashPhotoResponse(
    val results: ArrayList<UnsplashPhoto>
)

data class UnsplashPhoto(
    val id: String,
    val urls: Urls,
)

data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)
