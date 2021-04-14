package com.thesis.android_challenge_w3.activity.signin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.thesis.android_challenge_w3.R
import com.thesis.android_challenge_w3.activity.profile.ProfileActivity
import com.thesis.android_challenge_w3.activity.signup.SignUpActivity
import com.thesis.android_challenge_w3.databinding.ActivitySignInBinding
import com.thesis.android_challenge_w3.model.User

class SignInActivity : AppCompatActivity() {

    companion object {
        const val USER_KEY = "USER_KEY"
    }

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setupViewModelBinding()
    }

    override fun onStop() {
        super.onStop()
        viewModel.clear()
    }

    private fun setupViewModelBinding() {
        viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.lifecycleOwner = this
        binding.signInViewModel = viewModel
        binding.apply {
            btnSignUp.setOnClickListener {
                val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

        viewModel.isSignInSucceed.observe(this, Observer { user ->
            user?.let {
                showToastMessage("Sign in Successful")
                startProfileActivity(user)
            }

        })

        viewModel.errorMessage.observe(this, Observer { message ->
            message?.let {
                showToastMessage(message)

            }
        })
    }

    private fun startProfileActivity(user: User) {
        val bundle = Bundle()
        bundle.putParcelable(USER_KEY, user)
        val intent = Intent(this@SignInActivity, ProfileActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun showToastMessage(value: String) {
        Toast.makeText(this@SignInActivity, value, Toast.LENGTH_SHORT).show()
    }
}