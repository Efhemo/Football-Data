package com.efhems.football.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.efhems.football.R
import com.efhems.football.model.Standing
import kotlinx.android.synthetic.main.table_item.view.*

class TableRCAdapter : ListAdapter<Standing, TableRCAdapter.ItemViewholder>(DiffCallbackTable()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.table_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Standing) = with(itemView) {

            this.position.text = item.position.toString()
            this.short_name.text = item.name

            Glide.with(this).load(item.crestUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.soccer_black))
                .apply(RequestOptions.errorOf(R.drawable.soccer_black))
                .apply(RequestOptions.centerCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(itemView.logo)

            this.point.text = item.points.toString()
            this.goals.text = item.goalDifference.toString()
            this.match_day.text = item.playedGames.toString()
        }
    }
}

class DiffCallbackTable : DiffUtil.ItemCallback<Standing>() {
    override fun areItemsTheSame(oldItem: Standing, newItem: Standing): Boolean {
        return oldItem.position == newItem.position
    }

    override fun areContentsTheSame(oldItem: Standing, newItem: Standing): Boolean {
        return oldItem == newItem
    }
}