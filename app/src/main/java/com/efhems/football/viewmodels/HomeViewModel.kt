
package com.efhems.football.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.efhems.football.database.getDatabase
import com.efhems.football.model.Competitions
import com.efhems.football.model.TodayFixtures
import com.efhems.football.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

const val areas = 2077
const val plan = "TIER_TWO"

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = Repository(database)

    private val _fixtures: MutableLiveData<List<TodayFixtures>> = MutableLiveData()
    val fixtures: LiveData<List<TodayFixtures>> get() = _fixtures

    val allComp: LiveData<List<Competitions>>
        get() = repository.competitions()


    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        todayfix()
        compettions()
    }

    fun todayfix() {
        viewModelScope.launch {
            _fixtures.value = repository.todayFixtures()
        }
    }

    fun compettions() {
        viewModelScope.launch {
            repository.competitions(areas, plan )
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }



}
