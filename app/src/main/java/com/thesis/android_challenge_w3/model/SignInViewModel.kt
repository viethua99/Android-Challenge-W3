package com.thesis.android_challenge_w3.model

import androidx.lifecycle.ViewModel

class SignInViewModel : ViewModel() {
    var user : User = User(fullName = "", email = "", password = "", phoneNumber = "")
}