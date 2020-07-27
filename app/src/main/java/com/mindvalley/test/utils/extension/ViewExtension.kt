package com.mindvalley.test.utils.extension

import android.content.ContextWrapper
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


fun View.getParentActivity(): AppCompatActivity? {
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

fun ImageView.loadImage(url: String?, inCircle: Boolean = false) {
    url?.let {

        val req = Glide.with(context)
            .load(it)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)

        if (inCircle)
            req.circleCrop()

        req.into(this)

//        Glide
//            .with(context)
//            .load(url)
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
//            .circleCrop()
//            .skipMemoryCache(true)
//            .into(this);
    }
}

fun Uri.addUriParameter(key: String, newValue: String): Uri {
    val params = queryParameterNames
    val newUri = buildUpon().clearQuery()
    var isSameParamPresent = false
    for (param in params) {
        // if same param is present override it, otherwise add the old param back
        newUri.appendQueryParameter(param,
            if (param == key) newValue else getQueryParameter(param))
        if (param == key) {
            // make sure we do not add new param again if already overridden
            isSameParamPresent = true
        }
    }
    if (!isSameParamPresent) {
        // never overrode same param so add new passed value now
        newUri.appendQueryParameter(key,
            newValue)
    }
    return newUri.build()
}