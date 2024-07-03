package com.example.activitykiller.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MessageViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()

    val message: LiveData<String> = _message

    fun setMessage(newMessage: String) {
        _message.value = newMessage
    }
}
