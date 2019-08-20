package com.efhems.football.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Competitions(@PrimaryKey val id: Long, val name: String)


data class Standing(val position: Int,
                    val crestUrl: String,
                    val name: String,
                    val playedGames: Int,
                    val goalDifference: Int,
                    val points: Int
)

data class
Teams( val id: Int,
                 val crestUrl: String,
                 val name: String
)
