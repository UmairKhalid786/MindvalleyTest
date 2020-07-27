package com.mindvalley.test.model.converters;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mindvalley.test.model.responses.episodes.IconAsset


class IconAssetConverter {
    @TypeConverter
    fun listToJson(value: IconAsset?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToObject(value: String) = Gson().fromJson(value, IconAsset::class.java)
}
