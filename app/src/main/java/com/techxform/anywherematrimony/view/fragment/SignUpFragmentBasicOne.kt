package com.techxform.anywherematrimony.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.techxform.anywherematrimony.data.FilterItem
import com.techxform.anywherematrimony.databinding.FragmentSignUpBasicOneBinding
import com.techxform.anywherematrimony.helpers.event.EventObserver
import com.techxform.anywherematrimony.view.activity.SignUpActivity
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel.Companion.CAST_FILTER
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel.Companion.PROFILE_CREATED_FILTER
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel.Companion.RELIGION_FILTER
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SignUpFragmentBasicOne : BaseFragment() {
    private val LOG_TAG = javaClass.name
    private val filtersViewModel: FiltersViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBasicOneBinding
    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBasicOneBinding.inflate(inflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeData()
    }

    private fun initViews(){
        if(filtersViewModel.profileCreatedList.value?.peekContent().isNullOrEmpty())
          filtersViewModel.getFilters(PROFILE_CREATED_FILTER)

        if(filtersViewModel.religionList.value?.peekContent().isNullOrEmpty())
         filtersViewModel.getFilters(RELIGION_FILTER)

        binding.profileCreatedSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
           override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               authViewModel.signUpInput.profile_created_by = p2.toString()
           }
           override fun onNothingSelected(p0: AdapterView<*>?) {
               Log.d(LOG_TAG, "Nothing selected")
           }
       }

        binding.religionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                filtersViewModel.religionList.value?.peekContent()?.get(p2)?.let {
                    authViewModel.signUpInput.religion = p2.toString()
                    filtersViewModel.getFilters(CAST_FILTER, it.id.toString())
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG, "Nothing selected")
            }
        }

        binding.castSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
           override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
               authViewModel.signUpInput.caste = p2.toString()
           }
           override fun onNothingSelected(p0: AdapterView<*>?) {
               Log.d(LOG_TAG, "Nothing selected")
           }
       }

        binding.nextBtn.setOnClickListener {
            setData()
            (requireActivity() as? SignUpActivity)?.displayFragmentB()
        }
    }

    private fun subscribeData(){
        filtersViewModel.profileCreatedList.observe(viewLifecycleOwner, EventObserver { profileCreatedLIst ->
            activity?.let {
                binding.profileCreatedSpinner.adapter = ArrayAdapter<FilterItem>(
                   it,
                    android.R.layout.simple_list_item_1,
                    profileCreatedLIst
                )
            }

        })

        filtersViewModel.religionList.observe(
            viewLifecycleOwner,
            EventObserver { religionList ->
                activity?.let {
                    binding.religionSpinner.adapter = ArrayAdapter<FilterItem>(
                        it,
                        android.R.layout.simple_list_item_1,
                        religionList
                    )
                }

            })

        filtersViewModel.castList.observe(
            viewLifecycleOwner,
            EventObserver { castList ->
                activity?.let {
                    binding.castSpinner.adapter = ArrayAdapter<FilterItem>(
                        it,
                        android.R.layout.simple_list_item_1,
                        castList
                    )
                }

            })

    }

    private fun setData(){
        authViewModel.signUpInput.apply {
            candidate_name = binding.nameEt.text.toString()
            email = binding.emailEt.text.toString()
            mobile = binding.mobileEt.text.toString()
            date_of_birth = binding.dayEt.text.toString()+"-"+binding.monthEt.text.toString()+"-"+binding.yearEt.text.toString()
            gender = if(binding.maleRb.isChecked) "1" else "2"
        }
    }
}