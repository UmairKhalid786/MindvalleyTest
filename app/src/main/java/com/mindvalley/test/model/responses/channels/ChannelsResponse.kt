package com.mindvalley.test.model.responses.channels
import com.google.gson.annotations.SerializedName


data class ChannelsResponse(
        @SerializedName("data") var data: Data
)


