package com.techxform.anywherematrimony.view.fragment

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.FilterItem
import com.techxform.anywherematrimony.databinding.FragmentSignUpBasicOneBinding
import com.techxform.anywherematrimony.databinding.FragmentSignUpBasicTwoBinding
import com.techxform.anywherematrimony.extensions.empty
import com.techxform.anywherematrimony.helpers.event.EventObserver
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragmentBasicTwo : BaseFragment() {
    private val LOG_TAG = javaClass.name

    private val filtersViewModel: FiltersViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBasicTwoBinding
    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBasicTwoBinding.inflate(inflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeData()

    }

    private fun initViews(){
        filtersViewModel.getFilters(FiltersViewModel.COUNTRY_FILTER)
        binding.countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                authViewModel.signUpInput.country = p2.toString()
                filtersViewModel.countryList.value?.peekContent()?.get(p2)?.let {
                    filtersViewModel.getFilters(FiltersViewModel.STATE_FILTER,it.id.toString())
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG,"Nothing selected")
            }
        }

        binding.stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                authViewModel.signUpInput.state = p2.toString()
                filtersViewModel.stateList.value?.peekContent()?.get(p2)?.let {
                    filtersViewModel.getFilters(FiltersViewModel.DISTRICT_FILTER,it.id.toString())
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG,"Nothing selected")
            }
        }

        binding.districtSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                authViewModel.signUpInput.district = p2.toString()
                filtersViewModel.districtList.value?.peekContent()?.get(p2)?.let {
                    filtersViewModel.getFilters(FiltersViewModel.PLACES_FILTER,it.id.toString())
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG,"Nothing selected")
            }
        }

        binding.placeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                authViewModel.signUpInput.place = p2.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG,"Nothing selected")
            }
        }

        binding.signUpBtn.setOnClickListener{
            setData()
            showProgress()
            authViewModel.signUpUser()
        }
    }

    private fun subscribeData(){

        filtersViewModel.countryList.observe(viewLifecycleOwner, EventObserver { countriesList ->
            activity?.let {
                binding.countrySpinner.adapter = ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    countriesList
                )
            }

        })
        filtersViewModel.stateList.observe(viewLifecycleOwner, EventObserver { stateList ->
            activity?.let {
                binding.stateSpinner.adapter = ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    stateList
                )
            }

        })

        filtersViewModel.districtList.observe(viewLifecycleOwner, EventObserver { districtList ->
            activity?.let {
                binding.districtSpinner.adapter = ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    districtList
                )
            }

        })

        filtersViewModel.cityList.observe(viewLifecycleOwner, EventObserver { cityList ->
            activity?.let {
                binding.placeSpinner.adapter = ArrayAdapter(
                    it,
                    android.R.layout.simple_list_item_1,
                    cityList
                )
            }

        })

        authViewModel.signUpOutput.observe(viewLifecycleOwner) {
            hideProgress()
            it.userid?.let {
                Toast.makeText(context, "Signed Up successfully", Toast.LENGTH_SHORT).show()
                showOtpBox(String.empty)
//                authViewModel.checkLogin(authViewModel.signUpInput.username.toString(),authViewModel.signUpInput.password.toString())
            } ?: run {
                showOtpBox(String.empty)

                Toast.makeText(context, "Something went wrong.. Try again after sometime", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setData(){
        authViewModel.signUpInput.username = binding.usernameEt.text.toString()
        authViewModel.signUpInput.password = binding.passwordEt.text.toString()
    }

    private fun showOtpBox(title: String) {
        val dialog = activity?.let { Dialog(it) }
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.setCancelable(false)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent);
        dialog?.setContentView(R.layout.otp_dialog)
        /*val body = dialog.findViewById(R.id.body) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.yesBtn) as Button
        val noBtn = dialog.findViewById(R.id.noBtn) as TextView
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        noBtn.setOnClickListener { dialog.dismiss() }*/
        dialog?.show()

    }

}