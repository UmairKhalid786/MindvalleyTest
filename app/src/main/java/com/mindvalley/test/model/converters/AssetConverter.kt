package com.mindvalley.test.model.converters;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mindvalley.test.model.responses.episodes.Asset


class AssetConverter {
    @TypeConverter
    fun listToJson(value: Asset?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToObject(value: String) = Gson().fromJson(value, Asset::class.java)
}
