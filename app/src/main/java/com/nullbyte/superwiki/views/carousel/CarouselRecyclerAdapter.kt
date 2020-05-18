package com.nullbyte.superwiki.views.carousel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nullbyte.superwiki.databinding.ItemViewPager2Binding
import com.nullbyte.superwiki.models.Image
import kotlinx.android.synthetic.main.item_view_pager2.view.*

class CarouselRecyclerAdapter(list: List<Image>) :
    RecyclerView.Adapter<CarouselRecyclerAdapter.ViewHolder>() {
    private var listUrl: List<Image>? = ArrayList()

    init {
        listUrl = list
    }

    class ViewHolder(itemView: ItemViewPager2Binding) : RecyclerView.ViewHolder(itemView.root) {
        fun bindUrl(imageUrl: String) {
            Glide.with(itemView.context)
                .load(imageUrl)
                .fitCenter()
                .into(itemView.iv_view_pager2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemViewPager2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return listUrl?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUrl(listUrl?.get(position)?.url ?: "")
    }

    fun updateList(list: List<Image>) {
        listUrl = list
        notifyDataSetChanged()
    }
}