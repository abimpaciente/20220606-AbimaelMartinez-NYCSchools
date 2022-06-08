package com.example.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.School
import com.example.model.StateUI
import com.example.model.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
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

    var currentSchoolModel: School? = null

    init {
        getSchools()
    }


    fun getSchools() {
        coroutineScope.launch {
            repository.getSchools().collect { state ->
                _schoolLiveData.postValue(state)
            }
        }
    }

    fun getSchoolsSat(schoolModel: School) {
        coroutineScope.launch {
            repository.getSchoolsSat(schoolModel.dbn).collect { state ->
                _scoreLiveData.postValue(state)
            }
        }
    }

    fun setSchool(nycSchoolModel: School) {
        currentSchoolModel = nycSchoolModel
//        getSchoolsSat(nycSchool.dbn)
    }

}
