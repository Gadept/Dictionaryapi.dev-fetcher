package com.g_adept.intelliastesttask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.g_adept.intelliastesttask.data.entities.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM word_table")
    fun loadWords(): List<Word>

    @Query("SELECT EXISTS(SELECT * FROM word_table WHERE word= :word)")
    fun isWordExists(word: String): Boolean

    @Query("SELECT * FROM word_table WHERE word = :word")
    fun loadWord(word: String): /*LiveData<*/Word/*>*/

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: List<Word>)
}