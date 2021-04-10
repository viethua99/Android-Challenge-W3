package com.thesis.android_challenge_w3.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thesis.android_challenge_w3.store.DataStore

class SignInViewModel : ViewModel() {
    var user: User = User(fullName = "", email = "", password = "", phoneNumber = "")
    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()
    val isSignInSuccess:MutableLiveData<Boolean> = MutableLiveData()

    fun onClickSignIn(){
        user = User("",email.value.toString(),password.value.toString(),"")
        isSignInSuccess.value = DataStore.instance.isLoginSucceed(user.email.toString(),user.password.toString())
    }



}