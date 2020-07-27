package com.mindvalley.test.model.responses.channels;

import com.google.gson.annotations.SerializedName
import com.mindvalley.test.model.responses.Media

class LatestMedia(
    @SerializedName("type") var type: String
) : Media()
