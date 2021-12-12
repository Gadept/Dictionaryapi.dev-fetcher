package com.g_adept.intelliastesttask.data.remote

import com.g_adept.intelliastesttask.data.entities.Word
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WordService {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWord(@Path("word") word: String): Response<List<Word>>
}