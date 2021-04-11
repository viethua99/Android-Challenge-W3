package com.thesis.android_challenge_w3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.thesis.android_challenge_w3.R
import com.thesis.android_challenge_w3.databinding.ActivityProfileBinding
import com.thesis.android_challenge_w3.dialog.EditDialog
import com.thesis.android_challenge_w3.model.ProfileViewModel
import com.thesis.android_challenge_w3.store.DataStore

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        setupViewModel()
        binding.apply {
            val user = DataStore.instance.currentUser
            user?.let {
                viewModel.setFullNameUserProfile(user.fullName)
                viewModel.setEmailUserProfile(user.email)
                viewModel.setPhoneNumberUserProfile(user.phoneNumber)
            }
            tvFullName.setOnClickListener {
                setupAlertDialog("Edit Full Name", "Enter your full name",tvFullName.text.toString(),object:EditDialog.EditDialogCallback {
                    override fun onConfirmClicked(data: String) {
                        user?.fullName = data
                        viewModel.setFullNameUserProfile(data)
                        showToastMessage(data)
                    }
                })
            }
            tvEmail.setOnClickListener {
               setupAlertDialog("Edit E-mail ", "Enter your e-mail",tvEmail.text.toString(),object :EditDialog.EditDialogCallback {
                   override fun onConfirmClicked(data: String) {
                       user?.email = data
                       viewModel.setEmailUserProfile(data)
                       showToastMessage(data)

                   }
               })
            }
            tvPhoneNumber.setOnClickListener {
                setupAlertDialog("Edit Phone Number ", "Enter your phone number",tvPhoneNumber.text.toString(),object :EditDialog.EditDialogCallback {
                    override fun onConfirmClicked(data: String) {
                        user?.phoneNumber = data
                        viewModel.setPhoneNumberUserProfile(data)
                        showToastMessage(data)
                    }
                })
            }
        }
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.user = viewModel.user.value
        viewModel.user.observe(this , Observer{ user ->
            binding.tvFullName.text = user.fullName
            binding.tvUserName.text = user.fullName
            binding.tvEmail.text = user.email
            binding.tvPhoneNumber.text = user.phoneNumber
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        DataStore.instance.currentUser = null
    }


    private fun setupAlertDialog(title: String, textHint: String,initData:String,editDialogCallback: EditDialog.EditDialogCallback) {
        val dialog = EditDialog(this,title,textHint,initData)
        dialog.setEditDialogCallback(editDialogCallback)
        dialog.show()
        dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    }

    private fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}