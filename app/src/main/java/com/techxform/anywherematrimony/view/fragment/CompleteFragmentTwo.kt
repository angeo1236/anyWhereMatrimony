package com.techxform.anywherematrimony.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.NameIdObject
import com.techxform.anywherematrimony.databinding.FragmentCompleteRegistrationTwoBinding
import com.techxform.anywherematrimony.view.activity.CompleteRegistration
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CompleteFragmentTwo : BaseFragment() {
    private val LOG_TAG = javaClass.name
    private val filtersViewModel: FiltersViewModel by viewModel()
    private lateinit var binding: FragmentCompleteRegistrationTwoBinding
    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteRegistrationTwoBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            (requireActivity() as? CompleteRegistration)?.displayFragmentC()
        }
        binding.siblingsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.yesRadioButton) {
                binding.siblingsDetailsLayout.visibility = View.VISIBLE
            } else {
                binding.siblingsDetailsLayout.visibility = View.GONE
            }
        }
        subscribeData()
    }

    private fun subscribeData() {
        authViewModel.registrationFiltersOutput.observe(viewLifecycleOwner) {
            binding.familyStatusRadioButtonList.submitList(it.family_status as ArrayList<NameIdObject>)

        }
        context?.let { mContext ->
            val brothersMarriedAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, android.R.layout.simple_spinner_item,getCount())
            binding.brothersMarriedSpinner.adapter = brothersMarriedAdapter

            val brothersUnmarriedAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, android.R.layout.simple_spinner_item,getCount())
            binding.brothersUnmarriedSpinner.adapter = brothersUnmarriedAdapter

            val sistersMarriedAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, android.R.layout.simple_spinner_item,getCount())
            binding.sistersMarriedSpinner.adapter = sistersMarriedAdapter

            val sistersUnmarriedAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, android.R.layout.simple_spinner_item,getCount())
            binding.sistersUnmarriedSpinner.adapter = sistersUnmarriedAdapter
        }

    }

    private fun getCount(): MutableList<NameIdObject> {
        val countList = mutableListOf<NameIdObject>()
        for (i in 0..10) {
            val nameIdObject = NameIdObject()
            nameIdObject.id = i.toString()
            nameIdObject.name = i.toString()
            countList.add(nameIdObject)
        }
        return countList
    }
}