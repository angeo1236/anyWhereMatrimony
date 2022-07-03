package com.techxform.anywherematrimony.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.FilterItem
import com.techxform.anywherematrimony.databinding.FragmentSignUpBasicOneBinding
import com.techxform.anywherematrimony.databinding.FragmentSignUpBasicTwoBinding
import com.techxform.anywherematrimony.helpers.event.EventObserver
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragmentBasicTwo : Fragment() {
    private val LOG_TAG = javaClass.name

    private val filtersViewModel: FiltersViewModel by viewModel()
    private lateinit var binding: FragmentSignUpBasicTwoBinding

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
                filtersViewModel.districtList.value?.peekContent()?.get(p2)?.let {
                    filtersViewModel.getFilters(FiltersViewModel.PLACES_FILTER,it.id.toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d(LOG_TAG,"Nothing selected")
            }

        }
    }

    private fun subscribeData(){

        filtersViewModel.countryList.observe(viewLifecycleOwner, EventObserver { countriesList ->
            activity?.let {
                binding.countrySpinner.adapter = ArrayAdapter<FilterItem>(
                    it,
                    android.R.layout.simple_list_item_1,
                    countriesList
                )
            }

        })
        filtersViewModel.stateList.observe(viewLifecycleOwner, EventObserver { stateList ->
            activity?.let {
                binding.stateSpinner.adapter = ArrayAdapter<FilterItem>(
                    it,
                    android.R.layout.simple_list_item_1,
                    stateList
                )
            }

        })

        filtersViewModel.districtList.observe(viewLifecycleOwner, EventObserver { districtList ->
            activity?.let {
                binding.districtSpinner.adapter = ArrayAdapter<FilterItem>(
                    it,
                    android.R.layout.simple_list_item_1,
                    districtList
                )
            }

        })

        filtersViewModel.cityList.observe(viewLifecycleOwner, EventObserver { cityList ->
            activity?.let {
                binding.placeSpinner.adapter = ArrayAdapter<FilterItem>(
                    it,
                    android.R.layout.simple_list_item_1,
                    cityList
                )
            }

        })
    }

}