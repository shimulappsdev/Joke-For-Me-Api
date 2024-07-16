package com.shimul.jokes_for_me.view.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shimul.jokes_for_me.data.remote.JokeClient
import com.shimul.jokes_for_me.data.ResponseJokes
import kotlinx.coroutines.launch

class JokesHomeViewModel : ViewModel() {

    val jokesData = MutableLiveData<ResponseJokes>()
    val _jokesData
        get() = jokesData

    init {
        getRandomJokes()
    }

    fun getRandomJokes() {
        viewModelScope.launch {
            val response = JokeClient.jokesApiService.getJokes()
            if (response.isSuccessful){
                jokesData.postValue(response.body())
            }
        }
    }


}