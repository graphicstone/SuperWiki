package com.nullbyte.superwiki.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.models.Result
import com.nullbyte.superwiki.views.activities.DetailedViewActivity
import de.hdodenhof.circleimageview.CircleImageView

class SearchListAdapter(private val searchList: List<Result>) :
    RecyclerView.Adapter<SearchViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        mContext = parent.context
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_complete_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.name.text = searchList[position].name
        if (searchList[position].biography.publisher.isBlank())
            holder.publisher.text = mContext.getString(R.string.na)
        else
            holder.publisher.text = searchList[position].biography.publisher
        if (searchList[position].biography.fullName.isBlank())
            holder.realName.text = searchList[position].name
        else
            holder.realName.text = searchList[position].biography.fullName
        Glide.with(mContext)
            .load(searchList[position].image.url)
            .fallback(R.drawable.ic_account_circle_black_24dp)
            .placeholder(R.drawable.ic_account_circle_black_24dp)
            .centerCrop()
            .into(holder.avatar)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailedViewActivity::class.java)
            intent.putExtra("id", searchList[position].id)
            intent.putExtra("name", searchList[position].name)
            mContext.startActivity(intent)
        }
    }

}

class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.tv_name)
    val realName: TextView = itemView.findViewById(R.id.tv_real_name)
    val publisher: TextView = itemView.findViewById(R.id.tv_publisher)
    val avatar: CircleImageView = itemView.findViewById(R.id.civ_avatar)
}
