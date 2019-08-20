package com.efhems.football.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efhems.football.model.Competitions

@Dao
interface Dao {

    //Insert and get list of competitions
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCompetions(competitions: List<Competitions>)

    @Query("SELECT * FROM competitions")
    fun competitions(): LiveData<List<Competitions>>

    /*//Insert and get list of Standing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStandings(standings: List<Standing>)

    @Query("SELECT * FROM standing")
    fun standings(): LiveData<List<Standing>>

    //Insert and get list of Teams
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTeams(teams: List<Teams>)

    @Query("SELECT * FROM teams")
    fun teams(): LiveData<List<Teams>>*/

}