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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w3.model.SignUpViewModel


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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupViewModel()
        binding.signUpViewModel = viewModel
        binding.apply {
            btnSignIn.setOnClickListener {
                startLoginActivity()
            }
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        viewModel.isSignUpSucceed.observe(this,
            Observer<Boolean> {
                if (it) {
                    Toast.makeText(this@SignUpActivity, "Sign Up Successful", Toast.LENGTH_SHORT)
                        .show()
                    startLoginActivity()
                } else {
                    Toast.makeText(this@SignUpActivity, "Sign Up Failed", Toast.LENGTH_SHORT).show()

                }
            })

    }

    private fun startLoginActivity() {
        val intent = Intent(
            this@SignUpActivity,
            SignInActivity::class.java
        )
        startActivity(intent)
    }
}