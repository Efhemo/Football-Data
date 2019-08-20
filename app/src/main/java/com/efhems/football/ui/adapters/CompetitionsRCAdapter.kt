package com.efhems.football.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efhems.football.R
import com.efhems.football.model.Competitions
import kotlinx.android.synthetic.main.competitions_item.view.*

class CompetitionsRCAdapter(val listener: OnCompClickListener) : ListAdapter<Competitions, CompetitionsRCAdapter.ItemViewholder>(DiffCallbackComp()) {

    interface OnCompClickListener{
        fun onClick(comp: Competitions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.competitions_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Competitions) = with(itemView) {

            this.name.text = item.name

            setOnClickListener {
                listener.onClick(item)
            }
        }
    }
}

class DiffCallbackComp : DiffUtil.ItemCallback<Competitions>() {
    override fun areItemsTheSame(oldItem: Competitions, newItem: Competitions): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Competitions, newItem: Competitions): Boolean {
        return oldItem == newItem
    }
}