package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.databinding.ActivityCompleteRegistrationBinding
import com.techxform.anywherematrimony.databinding.ActivitySignUpBinding
import com.techxform.anywherematrimony.view.fragment.*
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CompleteRegistration : BaseActivity() {
    private lateinit var databinding: ActivityCompleteRegistrationBinding

    private lateinit var firstFragment : CompleteFragmentOne
    private lateinit var secondFragment : CompleteFragmentTwo
    private lateinit var thirdFragment : CompleteFragmentThree
    private lateinit var fourFragment : CompleteFragmentFour

    private val authViewModel: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        databinding = DataBindingUtil.inflate(inflater,R.layout.activity_complete_registration,frameContainer,true)

        firstFragment = CompleteFragmentOne()
        secondFragment = CompleteFragmentTwo()
        thirdFragment = CompleteFragmentThree()
        fourFragment = CompleteFragmentFour()
        authViewModel.getRegistrationFilters()
        displayFragmentA()

    }


    private fun displayFragmentA() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (firstFragment.isAdded) { // if the fragment is already in container
            ft.show(firstFragment)
        } else { // fragment needs to be added to frame container
            ft.add(databinding.fragmentContainerView.id, firstFragment, "One")
        }

        if (secondFragment.isAdded) {
            ft.hide(secondFragment)
        }
        if (thirdFragment.isAdded) {
            ft.hide(thirdFragment)
        }
        if (fourFragment.isAdded) {
            ft.hide(fourFragment)
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
        if (firstFragment.isAdded) {
            ft.hide(firstFragment)
        }
        if (thirdFragment.isAdded) {
            ft.hide(thirdFragment)
        }
        if (fourFragment.isAdded) {
            ft.hide(fourFragment)
        }
        ft.commit()
    }

    fun displayFragmentC() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (thirdFragment.isAdded) { // if the fragment is already in container
            ft.show(thirdFragment)
        } else { // fragment needs to be added to frame container
            ft.add(databinding.fragmentContainerView.id, thirdFragment, "Three")
        }
        if (firstFragment.isAdded) {
            ft.hide(firstFragment)
        }
        if (secondFragment.isAdded) {
            ft.hide(secondFragment)
        }
        if (fourFragment.isAdded) {
            ft.hide(fourFragment)
        }
        ft.commit()
    }

    fun displayFragmentD() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (fourFragment.isAdded) { // if the fragment is already in container
            ft.show(fourFragment)
        } else { // fragment needs to be added to frame container
            ft.add(databinding.fragmentContainerView.id, fourFragment, "Four")
        }
        if (firstFragment.isAdded) {
            ft.hide(firstFragment)
        }
        if (secondFragment.isAdded) {
            ft.hide(secondFragment)
        }
        if (thirdFragment.isAdded) {
            ft.hide(thirdFragment)
        }
        ft.commit()
    }

    override fun onBackPressed() {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()

        if(secondFragment.isVisible){
            displayFragmentA()
        }
        else if(thirdFragment.isVisible){
            displayFragmentB()
        }
        else if(fourFragment.isVisible){
            displayFragmentC()
        }
        else{
            finish()
        }
    }
}