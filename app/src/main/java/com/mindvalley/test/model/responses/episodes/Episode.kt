package com.mindvalley.test.model.responses.episodes;

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mindvalley.test.model.converters.ChannelTypeConverter
import com.mindvalley.test.model.responses.Media

@Entity
data class Episode(
    @field:PrimaryKey(autoGenerate = true)
    var id : Int,
    @TypeConverters(ChannelTypeConverter::class)
    @SerializedName("channel") var channel: Channel?,
    @SerializedName("type") var type: String
) : Media()



