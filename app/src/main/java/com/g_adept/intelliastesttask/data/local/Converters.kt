package com.g_adept.intelliastesttask.data.local

import androidx.room.TypeConverter
import com.g_adept.intelliastesttask.data.entities.Definitions
import com.g_adept.intelliastesttask.data.entities.Meanings
import com.g_adept.intelliastesttask.data.entities.Phonetics
import com.g_adept.intelliastesttask.data.entities.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class Converters {

    val gson = Gson()

    @TypeConverter
    fun fromDefinition(data: List<Definitions?>): String? = gson.toJson(data)

    @TypeConverter
    fun fromMeanings(data: List<Meanings?>): String? = gson.toJson(data)

    @TypeConverter
    fun fromPhonetics(data: List<Phonetics?>): String? = gson.toJson(data)

    @TypeConverter
    fun fromWord(data: Word?): String? = gson.toJson(data)

    @TypeConverter
    fun toDefinitions(data: String?): List<Definitions?> {
        val defType: Type = object : TypeToken<List<Definitions>>() {}.type
        return gson.fromJson(data, defType)
    }

    @TypeConverter
    fun toMeanings(data: String?): List<Meanings?> {
        val meanType: Type = object : TypeToken<List<Meanings>>() {}.type
        return gson.fromJson(data, meanType)
    }

    @TypeConverter
    fun toPhonetics(data: String?): List<Phonetics?> {
        val phoneticsType: Type = object : TypeToken<List<Phonetics>>() {}.type
        return gson.fromJson(data, phoneticsType)
    }

    @TypeConverter
    fun toWord(data: String?): Word? {
        val wordType: Type = object : TypeToken<Word>() {}.type
        return gson.fromJson(data, wordType)
    }
}