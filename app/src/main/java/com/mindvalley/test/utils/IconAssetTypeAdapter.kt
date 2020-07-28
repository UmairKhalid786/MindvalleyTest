package com.mindvalley.test.utils;

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import com.mindvalley.test.model.responses.episodes.IconAsset

class IconAssetTypeAdapter :
    TypeAdapter<IconAsset>() {
    override fun write(out: JsonWriter?, value: IconAsset?) {
        try {
            out?.beginObject();
            out?.name("thumbnailUrl")?.value(value?.thumbnailUrl)
            out?.endObject()
        }catch (e : Exception){

        }
    }

    override fun read(`in`: JsonReader?): IconAsset? {
        var newInstance: IconAsset? = IconAsset("")

        `in`?.let {
           try {
               newInstance = IconAsset("")
               if (`in`.peek() === JsonToken.NULL) `in`.nextNull()
               it.beginObject()
               while (it.hasNext()) {
                   val jsonTag = it.nextName()
                   if ("url" == jsonTag || "thumbnailUrl" == jsonTag) {
                       newInstance?.thumbnailUrl = it.nextString()
                   }
               }
               it.endObject()
           }catch (e : Exception){
               e.printStackTrace()
           }
        }


        return newInstance
    }
}
