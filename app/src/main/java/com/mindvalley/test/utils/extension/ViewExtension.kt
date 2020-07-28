package com.mindvalley.test.utils.extension

import android.content.ContextWrapper
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.ImageView
import androidx.annotation.AnimRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mindvalley.test.R
import kotlinx.android.synthetic.main.item_channel.view.*


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

fun ImageView.loadImage(
    url: String?,
    inCircle: Boolean = false,
    defaultImg: Int = R.drawable.logo) {

    url?.let {

        val req = Glide.with(context)
            .load(it)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true).placeholder(defaultImg)
            .error(defaultImg)

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
        newUri.appendQueryParameter(
            param,
            if (param == key) newValue else getQueryParameter(param)
        )
        if (param == key) {
            // make sure we do not add new param again if already overridden
            isSameParamPresent = true
        }
    }
    if (!isSameParamPresent) {
        // never overrode same param so add new passed value now
        newUri.appendQueryParameter(
            key,
            newValue
        )
    }
    return newUri.build()
}


fun RecyclerView.setupAnimation(@AnimRes anim : Int){

    val animation: LayoutAnimationController =
        AnimationUtils.loadLayoutAnimation(context, anim)

    setLayoutAnimation(animation)
}

fun RecyclerView.setHorizontalLayoutManager(){
    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
}

fun RecyclerView.setVerticalLayoutManager(){
    layoutManager = LinearLayoutManager(context)
}

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.GONE
}

fun View.visibility(show: Boolean){
    if (show) show() else hide()
}