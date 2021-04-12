package com.thesis.android_challenge_w3.activity.signup

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.thesis.android_challenge_w3.R
import androidx.databinding.DataBindingUtil
import com.thesis.android_challenge_w3.databinding.ActivitySignUpBinding
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w3.activity.signin.SignInActivity


/**
 * Created by Viet Hua on 04/03/2021.
 */

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setupViewModelBinding()

    }

    private fun setupViewModelBinding(){
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        binding.signUpViewModel = viewModel
        binding.apply {
            btnSignIn.setOnClickListener {
                startLoginActivity()
            }
        }

        viewModel.isSignUpSucceed.observe(this, Observer {
                if (it) {
                    showToastMessage("Sign Up Successful")
                    startLoginActivity()
                }
            })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this@SignUpActivity,message , Toast.LENGTH_SHORT).show()
        })

    }

    private fun startLoginActivity() {
        val intent = Intent(
            this@SignUpActivity,
            SignInActivity::class.java
        )
        startActivity(intent)
    }

    private fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}