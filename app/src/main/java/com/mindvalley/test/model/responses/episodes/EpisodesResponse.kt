package com.mindvalley.test.model.responses.episodes
import com.google.gson.annotations.SerializedName


data class EpisodesResponse(
        @SerializedName("data") var data: Data
)


