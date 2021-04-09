package com.thesis.android_challenge_w3.store

import com.thesis.android_challenge_w3.model.User

class DataStore private constructor(){
    private val userList = ArrayList<User>()
    var currentUser : User? = null

    companion object {
        val instance = DataStore()
    }

    fun isSignUpSucceed(fullName:String,email:String,password:String): Boolean{
        if(fullName.isEmpty() || email.isEmpty() || password.isEmpty()){
            return false
        } else {
            val user = User(fullName,email,password)
            userList.add(user)
            return true
        }
    }

    fun isLoginSucceed(email: String,password: String): Boolean {
        for(user in userList){
            if(user.email == email && user.password == password) {
                currentUser = user
                return true
            }
        }
        return false
    }
}