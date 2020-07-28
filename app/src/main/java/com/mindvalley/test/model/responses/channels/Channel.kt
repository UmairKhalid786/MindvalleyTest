package com.mindvalley.test.model.responses.channels;

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.mindvalley.test.model.converters.AssetConverter
import com.mindvalley.test.model.converters.IconAssetConverter
import com.mindvalley.test.model.converters.LatestMediaConverter
import com.mindvalley.test.model.converters.SeriesConverter
import com.mindvalley.test.model.responses.episodes.Asset
import com.mindvalley.test.model.responses.episodes.IconAsset

@Entity
data class Channel(
    @field:PrimaryKey(autoGenerate = true)
    var channelId: Int,
    @SerializedName("title") var title: String,
    @SerializedName("mediaCount") var mediaCount: Int,
    @TypeConverters(SeriesConverter::class)
    @SerializedName("series") var series: List<Series>?,
    @TypeConverters(LatestMediaConverter::class)
    @SerializedName("latestMedia") var latestMedia: List<LatestMedia>,
    @TypeConverters(IconAssetConverter::class)
    @SerializedName("iconAsset") var iconAsset: IconAsset?,
    @TypeConverters(AssetConverter::class)
    @SerializedName("coverAsset") var coverAsset: Asset
)
