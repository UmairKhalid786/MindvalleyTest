package com.mindvalley.test.model.responses.episodes;

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("media") var media: List<Episode>
)
