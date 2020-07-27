package com.mindvalley.test.ui.home.category

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mindvalley.test.R
import com.mindvalley.test.model.responses.categories.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryListAdapter: RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private lateinit var categories:List<Category>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int {
        return if(::categories.isInitialized) categories.size else 0
    }

    fun update(postList:List<Category>){
        this.categories = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View):RecyclerView.ViewHolder(view){

        fun bind(cat:Category){
            view.tvTitle.text = cat.name
        }
    }
}