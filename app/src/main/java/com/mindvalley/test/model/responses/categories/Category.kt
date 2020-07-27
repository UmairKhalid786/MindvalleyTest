package com.mindvalley.test.model.responses.categories;

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Category (
    @field:PrimaryKey(autoGenerate = true)
    var id : Int = -1,
    @SerializedName("name") val name : String
)