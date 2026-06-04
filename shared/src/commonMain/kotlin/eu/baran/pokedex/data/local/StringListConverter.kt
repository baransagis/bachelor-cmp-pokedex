package eu.baran.pokedex.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class StringListConverter {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return json.decodeFromString(value)
    }
}