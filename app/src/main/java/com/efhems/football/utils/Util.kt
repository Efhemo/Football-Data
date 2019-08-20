package com.efhems.football.utils

import com.efhems.football.model.Competitions
import com.efhems.football.model.Standing
import com.efhems.football.model.Teams
import com.efhems.football.model.TodayFixtures
import org.json.JSONException
import org.json.JSONObject

fun extractToFixture(x: String): List<TodayFixtures> {

    val todayFixtures = ArrayList<TodayFixtures>()

    var homeScore = 0
    var awayScore = 0

    val jsonObject = JSONObject(x)
    val matches = jsonObject.getJSONArray("matches")

    for (i in 0 until matches.length()) {

        val ob = matches.getJSONObject(i)
        val status = ob.getString("status")

        var matchday = 0



        try {
            if (status == "FINISHED" || status == "IN_PLAY" ) {
                homeScore = ob.getJSONObject("score").getJSONObject("fullTime").getInt("homeTeam")
                awayScore = ob.getJSONObject("score").getJSONObject("fullTime").getInt("awayTeam")

            } else if (status == "PAUSED") {
                homeScore = ob.getJSONObject("score").getJSONObject("halfTime").getInt("homeTeam")
                awayScore = ob.getJSONObject("score").getJSONObject("halfTime").getInt("awayTeam")
            }

            if(ob?.getInt("matchday") != null){
                matchday = ob.getInt("matchday")
            }
        }catch (e: JSONException){
           // Log.i("extractToFixture", e.message)

        }


        val todayFixture = TodayFixtures(
            ob.getLong("id"),
            ob.getString("status"),
            ob.getString("utcDate"),
            matchday,
            ob.getJSONObject("homeTeam").getString("name"),
            ob.getJSONObject("awayTeam").getString("name"),
            homeScore,
            awayScore,
            ob.getString("lastUpdated")
            )

        todayFixtures.add(todayFixture)
    }

    return todayFixtures
}


fun extractCompetitions(x: String): List<Competitions> {

    val competitionsList = ArrayList<Competitions>()
    val jsonObject = JSONObject(x)
    val competitions = jsonObject.getJSONArray("competitions")

    for (i in 0 until competitions.length()) {

        val ob = competitions.getJSONObject(i)
        val id = ob.getLong("id")

        val comp = Competitions(
            id,
            ob.getString("name")
        )
        competitionsList.add(comp)
    }
    return competitionsList
}


fun extractStanding(x: String): List<Standing> {

    val standings = ArrayList<Standing>()
    val jsonObject = JSONObject(x)
    val standing = jsonObject.getJSONArray("standings")

    val ob =  standing.getJSONObject(0)
    val table = ob.getJSONArray("table")

    for (i in 0 until table.length()) {

        val teams = table.getJSONObject(i)

        val pos = teams.getInt("position")
        val team = teams.getJSONObject("team")

        val stand = Standing(
            pos,
            team.getString("crestUrl"),
            team.getString("name"),
            teams.getInt("playedGames"),
            teams.getInt("goalDifference"),
            teams.getInt("points"))

        standings.add(stand)
    }
    return standings
}



fun extractTeam(x: String): List<Teams> {

    val teamList = ArrayList<Teams>()
    val jsonObject = JSONObject(x)
    val teams = jsonObject.getJSONArray("teams")
    for (i in 0 until teams.length()) {

        val teamOb = teams.getJSONObject(i)

        val id = teamOb.getInt("id")
        val name = teamOb.getString("name")
        val crestUrl = teamOb.getString("crestUrl")

        //Log.i("extractStanding", "Position is  " + id)

        val team = Teams(id, crestUrl,name)

        teamList.add(team)
    }
    return teamList
}