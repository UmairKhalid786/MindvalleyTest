package com.mindvalley.test.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.test.R
import com.mindvalley.test.model.responses.Media
import com.mindvalley.test.model.responses.episodes.Episode
import com.mindvalley.test.utils.extension.addUriParameter
import com.mindvalley.test.utils.extension.loadImage
import kotlinx.android.synthetic.main.item_media_card.view.*

class MediaListAdapter(var isForSeries: Boolean = false) : RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {

    //It was mentioned in document to limit list item to 6 only
    private val horizontalListLimit = 6
    private lateinit var media: List<Media>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        var layout = R.layout.item_media_card
        if (isForSeries)
            layout = R.layout.item_media_series_card

        return ViewHolder(LayoutInflater.from(parent.context).inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(media[position])
    }

    override fun getItemCount(): Int {
        return if (::media.isInitialized) showWithLimit(media.size) else 0
    }

    private fun showWithLimit(size: Int): Int {
        return if (size > horizontalListLimit) horizontalListLimit else size
    }


    fun updateMedia(postList: List<Media>) {
        this.media = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(m: Media) {

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