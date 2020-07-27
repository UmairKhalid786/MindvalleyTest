package com.mindvalley.test.ui.home.channel

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mindvalley.test.R
import com.mindvalley.test.model.responses.channels.Channel
import com.mindvalley.test.ui.home.MediaListAdapter
import com.mindvalley.test.utils.extension.loadImage
import kotlinx.android.synthetic.main.item_channel.view.*
import kotlinx.android.synthetic.main.item_headers.view.*
import kotlinx.android.synthetic.main.item_media_card.view.tvTitle


class ChannelsListAdapter : RecyclerView.Adapter<ChannelsListAdapter.ViewHolder>() {

    private lateinit var channels: ArrayList<Channel>
    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mediaView = LayoutInflater.from(parent.context).inflate(R.layout.item_channel, parent, false)

        val resId: Int = R.anim.layout_animation_slide_from_right
        val animation: LayoutAnimationController = AnimationUtils.loadLayoutAnimation(mediaView.context, resId)
        mediaView.mediaRv.setLayoutAnimation(animation)

        return ViewHolder(
            mediaView,
            viewPool
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(channels[position])
    }

    override fun getItemCount(): Int {
        return if (::channels.isInitialized) channels.size else 0
    }

    fun updateMedia(channels: ArrayList<Channel>) {

        // Due to short time I was not able to write Diff Utils for this but yes diff utils was going
        // to make this update lot smoother
        // val diffResult = DiffUtil.calculateDiff(MyDiffUtilCB(getItems(), items))
        if (this::channels.isInitialized){
            this.channels.clear()
        }

        this.channels = channels
        notifyDataSetChanged()

    }

    class ViewHolder(private val view: View ,val viewPool : RecyclerView.RecycledViewPool) : RecyclerView.ViewHolder(view) {
        var adapter : MediaListAdapter? = null

        fun bind(m: Channel) {
            var desc = " episodes"

            if (m.series.size > 0) {
                adapter = MediaListAdapter(true)
                adapter?.updateMedia(m.series)
                desc = " series"
            }else{
                adapter = MediaListAdapter(false)
                adapter?.updateMedia(m.latestMedia)
            }

            Log.e("UPDATE" , m.title)

            view.tvTitle.text = m.title
            view.tvDesc.text = m.mediaCount.toString() + desc
            view.thumbnailImg.loadImage(m.iconAsset?.url , true)
            view.mediaRv.layoutManager = LinearLayoutManager(view.context , RecyclerView.HORIZONTAL , false)
            view.mediaRv.adapter = adapter
        }
    }
}