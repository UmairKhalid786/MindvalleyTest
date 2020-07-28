package com.mindvalley.test.model.responses.episodes;

import com.google.gson.annotations.SerializedName

data class IconAsset(
    @SerializedName(value = "thumbnailUrl" , alternate = arrayOf("url")) var thumbnailUrl: String?
)
