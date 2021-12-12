package com.g_adept.intelliastesttask.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "word_table")
data class Word(
    @PrimaryKey
    @SerializedName("word") val word : String = "",
    @SerializedName("phonetic") val phonetic : String,
    @SerializedName("phonetics") val phonetics : List<Phonetics>,
    @SerializedName("origin") val origin : String,
    @SerializedName("meanings") val meanings : List<Meanings>
)