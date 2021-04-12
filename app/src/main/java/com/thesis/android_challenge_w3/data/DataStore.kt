package com.thesis.android_challenge_w3.data

import com.thesis.android_challenge_w3.model.User

class DataStore private constructor() {
    private val userList = ArrayList<User>()
    private lateinit var loginCallback: LoginCallback
    private lateinit var signUpCallback: SignUpCallback

    companion object {
        val instance = DataStore()

        const val FULL_NAME_FIELD = 0
        const val EMAIL_FIELD = 1
        const val PHONE_NUMBER_FIELD = 2

    }


    fun signUp(fullName: String, email: String, password: String) {
        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            signUpCallback.onFailed("Field cannot empty")
        } else {
            for (user in userList) {
                if (user.email == email) {
                    signUpCallback.onFailed("This email is already existed")
                    return
                }
            }
            val user = User(fullName, email, password)
            userList.add(user)
            signUpCallback.onSucceed()


        }
    }

    fun login(email: String, password: String) {
        for (user in userList) {
            if (user.email == email && user.password == password) {
                loginCallback.onSucceed(user)
                return
            }
        }
        loginCallback.onFailed("Cannot find any user")
    }

    fun getUserByEmail(email: String): User?{
        for(user in userList){
            if(user.email == email){
                return user
            }
        }
        return null
    }

    fun editUser(email: String, field: Int, value: String) {
        for (user in userList) {
            if (user.email == email) {
                when (field) {
                    FULL_NAME_FIELD -> {
                        user.fullName = value
                    }
                    EMAIL_FIELD -> {
                        user.email = value
                    }
                    PHONE_NUMBER_FIELD -> {
                        user.phoneNumber = value
                    }
                }
            }
        }
    }

    fun setSignUpCallback(signUpCallback: SignUpCallback) {
        this.signUpCallback = signUpCallback
    }

    fun setLoginCallback(loginCallback: LoginCallback) {
        this.loginCallback = loginCallback
    }

    interface LoginCallback {
        fun onSucceed(user: User)
        fun onFailed(message: String)

    }

    interface SignUpCallback {
        fun onSucceed()
        fun onFailed(message: String)
    }
}