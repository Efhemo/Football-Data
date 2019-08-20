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
import com.efhems.football.model.Teams
import kotlinx.android.synthetic.main.teams_item.view.*

class TeamRCAdapter : ListAdapter<Teams, TeamRCAdapter.ItemViewholder>(DiffCallbackTeam()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.teams_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Teams) = with(itemView) {

            this.name.text = item.name
            Glide.with(this).load(item.crestUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.soccer_black))
                .apply(RequestOptions.errorOf(R.drawable.soccer_black))
                .apply(RequestOptions.centerCropTransform())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(itemView.logo)

            setOnClickListener {
            }
        }
    }
}

class DiffCallbackTeam : DiffUtil.ItemCallback<Teams>() {

    override fun areItemsTheSame(oldItem: Teams, newItem: Teams): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Teams, newItem: Teams): Boolean {
        return oldItem == newItem
    }
}