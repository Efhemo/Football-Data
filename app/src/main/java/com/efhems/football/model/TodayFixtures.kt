package com.efhems.football.model

data class TodayFixtures(

    val id: Long,
    val status: String,
    val matchTime: String,
    val matchDay: Int,
    val homeTeam: String,
    val awayTeam: String,
    val homeScore: Int,
    val awayScore: Int,
    val lastUpdated: String

)