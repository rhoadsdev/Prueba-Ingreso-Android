package com.ceiba.pruebaingreso.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.pruebaingreso.domain.GetUsersUseCase
import com.ceiba.pruebaingreso.domain.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val users = MutableLiveData<List<User>?>()

    fun onLoadUsers() {
        viewModelScope.launch {
            val response = getUsersUseCase()
            if (!response.isNullOrEmpty()) {
                users.postValue(response)
            }
        }
    }
}