package com.mindvalley.test.model.converters;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mindvalley.test.model.responses.episodes.Channel

class ChannelTypeConverter {
    @TypeConverter
    fun listToJson(value: Channel?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToObject(value: String) = Gson().fromJson(value, Channel::class.java)
}
