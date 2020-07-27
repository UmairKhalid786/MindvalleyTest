package com.mindvalley.test.ui.home

import android.net.Uri
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.test.R
import com.mindvalley.test.model.responses.Media
import com.mindvalley.test.model.responses.episodes.Episode
import com.mindvalley.test.utils.dpToPixel
import com.mindvalley.test.utils.extension.addUriParameter
import com.mindvalley.test.utils.extension.loadImage
import kotlinx.android.synthetic.main.item_media_card.view.*
import kotlin.math.roundToInt

class MediaListAdapter(var isForSeries: Boolean = false) :

    RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {

    private lateinit var media: List<Media>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var layout = R.layout.item_media_card
        if (isForSeries)
            layout = R.layout.item_media_series_card

        return ViewHolder( LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(media[position], isForSeries)
    }

    override fun getItemCount(): Int {
        return if (::media.isInitialized) media.size else 0
    }


    fun updateMedia(postList: List<Media>) {
        this.media = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(m: Media, isForSeries: Boolean) {

            view.tvDescription.visibility = View.GONE

            view.tvTitle.text = m.title
            m.coverAsset?.let {
                var uri = Uri.parse(it.url)
                uri = uri.addUriParameter("transform", "w_360")
                view.mediaImg.loadImage(uri.toString())
            }

            if (m is Episode) {
                m.channel?.let {
                    view.tvDescription.text = it.title
                    view.tvDescription.visibility = View.VISIBLE
                }
            }
        }
    }
}