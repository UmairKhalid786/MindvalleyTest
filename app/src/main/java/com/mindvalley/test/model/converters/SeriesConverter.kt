package com.mindvalley.test.model.converters;

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mindvalley.test.model.responses.channels.Series


class SeriesConverter {
    @TypeConverter
    fun listToJson(value: List<Series>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Series>::class.java).toList()
}
