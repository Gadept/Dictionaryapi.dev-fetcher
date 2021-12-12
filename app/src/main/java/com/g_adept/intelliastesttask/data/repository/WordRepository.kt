package com.g_adept.intelliastesttask.data.repository

import com.g_adept.intelliastesttask.data.local.WordDao
import com.g_adept.intelliastesttask.data.remote.WordRemoteDataSource
import com.g_adept.intelliastesttask.data.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class WordRepository @Inject constructor(
    private val remoteDataSource: WordRemoteDataSource,
    private val localDataSource: WordDao
) {

    suspend fun getWord(word: String) = withContext(Dispatchers.IO) {
        return@withContext if (localDataSource.isWordExists(word.lowercase(Locale.getDefault()))) {
            Resource.success(listOf(localDataSource.loadWord(word.lowercase(Locale.getDefault()))))
        } else {
            val fetched = remoteDataSource.getWord(word)
            fetched.data?.let {
                localDataSource.insert(fetched.data)
            }
            fetched
        }
    }
}
