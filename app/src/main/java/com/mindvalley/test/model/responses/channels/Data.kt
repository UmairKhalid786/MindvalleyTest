package com.mindvalley.test.model.responses.channels;

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("channels") var channels: List<Channel>
)
