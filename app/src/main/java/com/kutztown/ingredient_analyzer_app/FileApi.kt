package com.kutztown.ingredient_analyzer_app
import okhttp3.MultipartBody
import retrofit2.Retrofit
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface FileApi {

    @POST("upload.php")
    @Multipart
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    )
    companion object {
        val instance: FileApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://foodingredientanalyzer.online/")
                .build()
                .create(FileApi::class.java)
        }
    }
}