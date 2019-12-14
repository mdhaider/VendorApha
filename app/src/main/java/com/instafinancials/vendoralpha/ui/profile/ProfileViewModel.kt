package com.instafinancials.vendoralpha.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _email = MutableLiveData<String>().apply {
        value = "uday@insta.com"
    }

    private val _phone = MutableLiveData<String>().apply {
        value = "9987654345"
    }

    private val _state = MutableLiveData<String>().apply {
        value = "Karnataka"
    }

    private val _country = MutableLiveData<String>().apply {
        value = "India"
    }


    val email: LiveData<String> = _email
    val phone: LiveData<String> = _phone
    val state: LiveData<String> = _state
    val country: LiveData<String> = _country

}