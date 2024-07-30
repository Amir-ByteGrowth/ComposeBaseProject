package com.example.composebaseproject.ui.screens.userlistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composebaseproject.data.models.UserData
import com.example.composebaseproject.data.remote.Resource
import com.example.composebaseproject.data.remote.ResponseTemplate
import com.example.composebaseproject.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _uiState: MutableStateFlow<Resource<ResponseTemplate<List<UserData>>>> =
        MutableStateFlow(Resource.initial())
    val uiState = _uiState.asStateFlow()


    init {
        _uiState.value = Resource.loading()
        getListNewApproach()
    }


    private fun getListNewApproach() {
        viewModelScope.launch {
            _uiState.value = mainRepository.getUserListNewApproach()
        }
    }

}