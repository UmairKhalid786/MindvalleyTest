package com.mindvalley.test.model.responses;

import com.google.gson.annotations.SerializedName
import com.mindvalley.test.model.responses.episodes.Asset

open class Media {
    @SerializedName("title") var title: String? = null
    @SerializedName("coverAsset") var coverAsset: Asset? = null
}

