package com.techxform.anywherematrimony.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.databinding.ActivityLoginBinding
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity() {

    private val authViewModel: AuthViewModel by viewModel()
    private val dataCaching: DataCaching by inject()
    private lateinit var databinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_login)


        databinding.loginBtn.setOnClickListener {

            showProgress("Validating user credentials")
            authViewModel.checkLogin(
                databinding.userIdEt.text.toString(),
                databinding.passwordEt.text.toString()
            )

        }

        authViewModel.loginOutput.observe(this) { loginOutput ->
            hideProgress()
            loginOutput.user_id?.let {
                dataCaching.setAccessToken(loginOutput.token)
                dataCaching.setEmail(loginOutput.email)
                dataCaching.setUserId(loginOutput.user_id)
                dataCaching.setUserImage(loginOutput.image)
                dataCaching.setUserName(loginOutput.user_name)

                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
            } ?: run {
                Toast.makeText(
                    this@LoginActivity,
                    "The entered credentials does not match with a registered user.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }
}