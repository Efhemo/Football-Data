
package com.efhems.football.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.efhems.football.database.getDatabase
import com.efhems.football.model.Standing
import com.efhems.football.model.Teams
import com.efhems.football.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

const val standardType = "TOTAL"

class DetailViewModel(application: Application, val id: Long) : AndroidViewModel(application) {


    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)
    private val repository = Repository(database)

    private val _standing: MutableLiveData<List<Standing>> = MutableLiveData()
    val standing: LiveData<List<Standing>?> get() = _standing


    private val _teams: MutableLiveData<List<Teams>> = MutableLiveData()
    val teams: LiveData<List<Teams>?> get() = _teams


    /**
     * init{} is called immediately when this ViewModel is created.
     */
    init {
        table()
        compettions()
    }

    fun table() {
        //Log.i(TAG, "table here o...")
        viewModelScope.launch {
            _standing.value = repository.table(id, standardType )
        }
    }

    fun compettions() {
        viewModelScope.launch {
           _teams.value =  repository.teams(id)
        }
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    class Factory(val app: Application, val id: Long) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(app, id) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}
