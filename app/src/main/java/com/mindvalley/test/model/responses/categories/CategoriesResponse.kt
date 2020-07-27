package com.mindvalley.test.model.responses.categories
import com.google.gson.annotations.SerializedName


data class CategoriesResponse(
        @SerializedName("data") val data : Data
)


