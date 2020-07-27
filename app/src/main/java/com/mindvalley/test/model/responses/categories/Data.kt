package com.mindvalley.test.model.responses.categories;

import com.google.gson.annotations.SerializedName

data class Data (
    @SerializedName("categories") val categories : List<Category>
)