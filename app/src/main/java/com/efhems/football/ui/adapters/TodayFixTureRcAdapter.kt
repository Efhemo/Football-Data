package com.efhems.football.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.efhems.football.R
import com.efhems.football.model.TodayFixtures
import kotlinx.android.synthetic.main.today_fixture_item.view.*

class TodayFixTureRcAdapter : ListAdapter<TodayFixtures, TodayFixTureRcAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.today_fixture_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TodayFixtures) = with(itemView) {

            this.status.text = item.status
            this.time.text = item.matchTime.substring(11, 16)
            this.match_day.text = "MD: "+ item.matchDay.toString()

            this.home_team.text = item.homeTeam
            this.away_team.text = item.awayTeam

            this.home_score.text = item.homeScore.toString()
            this.away_score.text = item.awayScore.toString()

            setOnClickListener {

            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<TodayFixtures>() {
    override fun areItemsTheSame(oldItem: TodayFixtures, newItem: TodayFixtures): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TodayFixtures, newItem: TodayFixtures): Boolean {
        return oldItem == newItem
    }
}