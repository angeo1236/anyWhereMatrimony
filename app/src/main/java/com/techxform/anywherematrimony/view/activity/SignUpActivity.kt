package com.techxform.anywherematrimony.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.databinding.ActivitySignUpBinding
import com.techxform.anywherematrimony.view.fragment.SignUpFragmentBasicOne
import com.techxform.anywherematrimony.view.fragment.SignUpFragmentBasicTwo


class SignUpActivity : AppCompatActivity() {
    private lateinit var databinding: ActivitySignUpBinding

    private lateinit var firstFragment : SignUpFragmentBasicOne
    private lateinit var secondFragment : SignUpFragmentBasicTwo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

            firstFragment = SignUpFragmentBasicOne()
            secondFragment = SignUpFragmentBasicTwo()

        displayFragmentA()

    }


    private fun displayFragmentA() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (firstFragment.isAdded) { // if the fragment is already in container
            ft.show(firstFragment)
        } else { // fragment needs to be added to frame container
            ft.add(databinding.fragmentContainerView.id, firstFragment, "One")
        }
        // Hide fragment B
        if (secondFragment.isAdded) {
            ft.hide(secondFragment)
        }
        ft.commit()
    }

    fun displayFragmentB() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (secondFragment.isAdded) { // if the fragment is already in container
            ft.show(secondFragment)
        } else { // fragment needs to be added to frame container
            ft.add(databinding.fragmentContainerView.id, secondFragment, "Two")
        }
        // Hide fragment A
        if (firstFragment.isAdded) {
            ft.hide(firstFragment)
        }
        ft.commit()
    }

    override fun onBackPressed() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()

        if(secondFragment.isVisible){
            displayFragmentA()
        }
        else{
            finish()
        }
    }
}