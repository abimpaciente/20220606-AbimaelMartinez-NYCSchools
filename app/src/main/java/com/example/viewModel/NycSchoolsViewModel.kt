package com.example.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.SchoolListResponse
import com.example.model.StateUI
import com.example.model.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NycSchoolsViewModel
@Inject constructor(
    private val repository: Repository,
    private val coroutineScope: CoroutineScope
) : ViewModel() {

    private val _schoolLiveData: MutableLiveData<StateUI> = MutableLiveData()
    val schoolLiveData: LiveData<StateUI> get() = _schoolLiveData

    private val _scoreLiveData: MutableLiveData<StateUI> = MutableLiveData(StateUI.Loading)
    val scoreLiveData: LiveData<StateUI> get() = _scoreLiveData

    var currentSchool: SchoolListResponse? = null

    init {
        getSchools()
    }

    private fun getSchools() {
        coroutineScope.launch {
            repository.getSchools().collect { state ->
                _schoolLiveData.postValue(state)
            }
        }
    }

    fun getSchoolsSat(dbn: String) {
        coroutineScope.launch {
            repository.getSchoolsSat(dbn).collect { state ->
                _scoreLiveData.postValue(state)
            }
        }
    }

    fun setSchool(nycSchool: SchoolListResponse?) {
        currentSchool = nycSchool
        _scoreLiveData.value = StateUI.Loading
    }

}
