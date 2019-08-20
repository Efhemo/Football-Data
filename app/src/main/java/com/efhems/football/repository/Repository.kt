package com.efhems.football.repository

import com.efhems.football.network.Network
import android.util.Log
import androidx.lifecycle.LiveData
import com.efhems.football.utils.extractToFixture
import com.efhems.football.database.FootballDatabase
import com.efhems.football.model.Competitions
import com.efhems.football.utils.extractCompetitions
import com.efhems.football.utils.extractStanding
import com.efhems.football.utils.extractTeam
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class Repository(private val database: FootballDatabase) {

    private val TAG = Repository::class.java.name

    private val key = "df7b382cb4a54c55a7beb3945faa9a5d"

    suspend fun todayFixtures() = withContext(Dispatchers.IO) {

        try {
            val response = Network.service.todayFixtures(key)

            if (response.isSuccessful && response.body() != null) {

                val fixturees = extractToFixture(response.body()!!.string())
                fixturees
            } else {
                null
            }
        } catch (e: HttpException) {
            //Log.i(TAG, e.message())
            null
        } catch (e: Exception) {
            //Log.i(TAG, e.message)
            null
        }
    }

    suspend fun competitions(areas: Int, plan: String) {
        withContext(Dispatchers.IO) {
            try {
                val response = Network.service.competitions(key, areas, plan)

                if (response.isSuccessful && response.body() != null) {
                    val competitions = extractCompetitions(response.body()!!.string())
                    database.dao.insertCompetions(competitions)

                } else {
                    null
                }
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }
    }

    suspend fun table(id: Long, standingType: String) = withContext(Dispatchers.IO) {
            try {
                val response = Network.service.table(key, id, standingType)

                if (response.isSuccessful && response.body() != null) {
                    val standings = extractStanding(response.body()!!.string())
                    //Log.i(TAG, "size is "+standings.size)
                    standings
                } else {
                    null
                }
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }

    suspend fun teams(id: Long) = withContext(Dispatchers.IO) {
            try {
                val response = Network.service.team(key, id)

                if (response.isSuccessful && response.body() != null) {
                    val teams = extractTeam(response.body()!!.string())
                    //Log.i(TAG, "size is "+teams.size)
                    teams
                } else {
                    null
                }
            } catch (e: HttpException) {
                null
            } catch (e: Exception) {
                null
            }
        }



    //database
    fun competitions(): LiveData<List<Competitions>> = database.dao.competitions()


}