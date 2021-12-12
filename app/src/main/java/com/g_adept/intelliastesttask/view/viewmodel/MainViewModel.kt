package com.g_adept.intelliastesttask.view.viewmodel

import androidx.lifecycle.ViewModel
import com.g_adept.intelliastesttask.data.entities.Word
import com.g_adept.intelliastesttask.data.repository.WordRepository
import com.g_adept.intelliastesttask.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WordRepository) : ViewModel() {

    suspend fun getWordDefinition(word: String): Resource<List<Word>> =
        repository.getWord(word)
}