package com.thesis.android_challenge_w3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.thesis.android_challenge_w3.R
import com.thesis.android_challenge_w3.databinding.ActivityProfileBinding
import com.thesis.android_challenge_w3.dialog.EditDialog
import com.thesis.android_challenge_w3.store.DataStore

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.apply {
            val user = DataStore.instance.currentUser
            user?.let {
                tvFullName.text = user.fullName
                tvUserName.text = user.fullName
                tvEmail.text = user.email
                tvPhoneNumber.text = user.phoneNumber

                tvFullName.setOnClickListener {
                    setupAlertDialog("Edit Full Name", "Enter your full name",tvFullName.text.toString(),object:EditDialog.EditDialogCallback {
                        override fun onConfirmClicked(data: String) {
                            user.fullName = data
                            tvFullName.text = data
                            tvUserName.text =data
                            showToastMessage(data)
                        }
                    })
                }

                tvEmail.setOnClickListener {
                   setupAlertDialog("Edit E-mail ", "Enter your e-mail",tvEmail.text.toString(),object :EditDialog.EditDialogCallback {
                       override fun onConfirmClicked(data: String) {
                           user.email = data
                           tvEmail.text = data
                           showToastMessage(data)

                       }
                   })
                }

                tvPhoneNumber.setOnClickListener {
                    setupAlertDialog("Edit Phone Number ", "Enter your phone number",tvPhoneNumber.text.toString(),object :EditDialog.EditDialogCallback {
                        override fun onConfirmClicked(data: String) {
                            user.phoneNumber = data
                            tvPhoneNumber.text = data
                            showToastMessage(data)

                        }
                    })
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DataStore.instance.currentUser = null
    }


    private fun setupAlertDialog(title: String, textHint: String,initData:String,editDialogCallback: EditDialog.EditDialogCallback) {
        val dialog = EditDialog(this,title,textHint,initData)
        dialog.setEditDialogCallback(editDialogCallback)
        dialog.show()
    }

    private fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}