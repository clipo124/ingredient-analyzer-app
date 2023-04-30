package com.kutztown.ingredient_analyzer_app

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface FileApi {

    @POST("/")
    @Multipart
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    )
    companion object {

        val instance: FileApi by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(FileApi::class.java)
        }
    }
}