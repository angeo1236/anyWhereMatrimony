package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.databinding.ActivityLoginBinding
import com.techxform.anywherematrimony.databinding.ActivityMainBinding
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var databinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        databinding = DataBindingUtil.inflate(inflater,R.layout.activity_main, frameContainer, true)

        databinding.loginTv.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        databinding.registerTv.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}