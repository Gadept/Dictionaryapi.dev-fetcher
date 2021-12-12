package com.g_adept.intelliastesttask.data.remote

import javax.inject.Inject

class WordRemoteDataSource @Inject constructor(private val wordService: WordService) :
    BaseDataSource() {

    suspend fun getWord(word: String) = getResult { wordService.getWord(word) }

}