package com.nullbyte.superwiki.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.models.CompleteList
import com.nullbyte.superwiki.views.activities.DetailedViewActivity
import de.hdodenhof.circleimageview.CircleImageView

class CompleteListAdapter(private val completeList: List<CompleteList>) :
    RecyclerView.Adapter<ViewHolder>() {

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_complete_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return completeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Position", position.toString())
        Log.i("Name", completeList[position].name)
        holder.name.text = completeList[position].name
        if(completeList[position].publisher.isNullOrBlank())
            holder.publisher.text = mContext.getString(R.string.na)
        else
            holder.publisher.text = completeList[position].publisher
        if (completeList[position].fullName.isBlank())
            holder.realName.text = completeList[position].name
        else
            holder.realName.text = completeList[position].fullName
        Glide.with(mContext)
            .load(completeList[position].profileImage)
            .fallback(R.drawable.ic_account_circle_black_24dp)
            .placeholder(R.drawable.ic_account_circle_black_24dp)
            .centerCrop()
            .into(holder.avatar)
        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, DetailedViewActivity::class.java)
            intent.putExtra("id", completeList[position].id.toString())
            intent.putExtra("name", completeList[position].name)
            mContext.startActivity(intent)
        }
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name: TextView = itemView.findViewById(R.id.tv_name)
    val realName: TextView = itemView.findViewById(R.id.tv_real_name)
    val publisher: TextView = itemView.findViewById(R.id.tv_publisher)
    val avatar: CircleImageView = itemView.findViewById(R.id.civ_avatar)
}
