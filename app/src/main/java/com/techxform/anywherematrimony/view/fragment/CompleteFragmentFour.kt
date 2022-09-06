package com.techxform.anywherematrimony.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techxform.anywherematrimony.databinding.FragmentCompleteRegistrationFourBinding
import com.techxform.anywherematrimony.extensions.convertToKeyPairList
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CompleteFragmentFour : BaseFragment() {
    private val LOG_TAG = javaClass.name
    private val filtersViewModel: FiltersViewModel by viewModel()
    private lateinit var binding: FragmentCompleteRegistrationFourBinding
    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteRegistrationFourBinding.inflate(inflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeData()
    }

    private fun subscribeData() {
        authViewModel.registrationFiltersOutput.observe(viewLifecycleOwner) {

            it.complexion?.let { complexionList ->
                binding.complexionMultiSpinner.setItems(complexionList.convertToKeyPairList()) {}
            }
            it.body_type?.let { bodyTypeList ->
                binding.bodyTypeMultiSpinner.setItems(bodyTypeList.convertToKeyPairList()) {}
            }
            it.physical_status?.let { physicalList ->
                binding.physicalStatusMultiSpinner.setItems(physicalList.convertToKeyPairList()) {}
            }
            it.smoking?.let { smokingList ->
                binding.smokingMultiSpinner.setItems(smokingList.convertToKeyPairList()) {}
            }
            it.diet?.let { dietList ->
                binding.dietMultiSpinner.setItems(dietList.convertToKeyPairList()) {}
            }
            it.occupation_category?.let { occupationList ->
                binding.occupationMultiSpinner.setItems(occupationList.convertToKeyPairList()) {}
            }
            it.employee_category?.let { employedList ->
                binding.employedCategoryMultiSpinner.setItems(employedList.convertToKeyPairList()) {}
            }
            it.income_category?.let { incomeList ->
                binding.incomeMultiSpinner.setItems(incomeList.convertToKeyPairList()) {}
            }
            it.marital_status?.let { maritalList ->
                binding.maritalStatusMultiSpinner.setItems(maritalList.convertToKeyPairList()) {}
            }
        }
    }
}