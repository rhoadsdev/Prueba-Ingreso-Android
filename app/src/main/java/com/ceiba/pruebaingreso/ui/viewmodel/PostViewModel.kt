package com.ceiba.pruebaingreso.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceiba.pruebaingreso.domain.GetPostsByUserUseCase
import com.ceiba.pruebaingreso.domain.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsByUserUseCase: GetPostsByUserUseCase
) : ViewModel() {
    val posts = MutableLiveData<List<Post>?>()

    fun onLoadPosts(userId: Int) {
        viewModelScope.launch {
            val response = getPostsByUserUseCase(userId)
            if (!response.isNullOrEmpty()) {
                posts.postValue(response)
            }
        }
    }
}