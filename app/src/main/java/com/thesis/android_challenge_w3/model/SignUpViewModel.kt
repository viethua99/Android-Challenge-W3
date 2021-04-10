package com.thesis.android_challenge_w3.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w3.store.DataStore

class SignUpViewModel : ViewModel() {
    val user = MutableLiveData<User>()
    var isSignUpSucceed = MutableLiveData<Boolean>()

    init {
        user.value = User()
    }
    fun signUp() {
        isSignUpSucceed.value =
            DataStore.instance.isSignUpSucceed(user.value!!.fullName, user.value!!.email, user.value!!.password)
    }
}