package com.thesis.android_challenge_w3.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.thesis.android_challenge_w3.R
import androidx.databinding.DataBindingUtil
import com.thesis.android_challenge_w3.databinding.ActivitySignUpBinding
import com.thesis.android_challenge_w3.store.DataStore
import android.widget.Toast


/**
 * Created by Viet Hua on 04/03/2021.
 */

class SignUpActivity: AppCompatActivity(){
    private lateinit var binding : ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        binding.apply {
            btnSignIn.setOnClickListener {
                startLoginActivity()
            }

            btnSignUp.setOnClickListener {
                val fullName = edtFullName.text.toString()
                val email = edtEmail.text.toString()
                val password = edtPassword.text.toString()
                if(DataStore.instance.isSignUpSucceed(fullName,email,password)){
                    Toast.makeText(this@SignUpActivity,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                    startLoginActivity()
                } else {
                    Toast.makeText(this@SignUpActivity,"Sign Up Failed",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun startLoginActivity(){
        val intent = Intent(this@SignUpActivity,
            SignInActivity::class.java)
        startActivity(intent)
    }
}