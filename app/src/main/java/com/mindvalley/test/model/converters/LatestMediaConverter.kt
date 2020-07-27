package com.mindvalley.test.model.converters;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mindvalley.test.model.responses.channels.LatestMedia


class LatestMediaConverter {
    @TypeConverter
    fun listToJson(value: List<LatestMedia>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<LatestMedia>::class.java).toList()
}
