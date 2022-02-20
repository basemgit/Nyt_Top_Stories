package com.basemibrahim.nyt_top_stories.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.basemibrahim.nyt_top_stories.data.Repository
import com.basemibrahim.nyt_top_stories.data.model.TopStoriesHomeResponse
import com.basemibrahim.photoslist.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<TopStoriesHomeResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<TopStoriesHomeResponse>> = _response

    fun fetchResponse() = viewModelScope.launch {
        repository.getTopStoriesHomeResponse().collect { values ->
            _response.value = values
        }
    }

}