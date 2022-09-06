package com.techxform.anywherematrimony.view.fragment

import android.R
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.techxform.anywherematrimony.data.NameIdObject
import com.techxform.anywherematrimony.databinding.FragmentCompleteRegistrationOneBinding
import com.techxform.anywherematrimony.extensions.addDefaultValue
import com.techxform.anywherematrimony.extensions.getSelectedObjectId
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.view.activity.CompleteRegistration
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CompleteFragmentOne : BaseFragment() {
    private val LOG_TAG = javaClass.name
    private lateinit var binding: FragmentCompleteRegistrationOneBinding
    private val authViewModel: AuthViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteRegistrationOneBinding.inflate(inflater,container,false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
           /* Toast.makeText(context, binding.heightSpinner.getSelectedObjectId(),Toast.LENGTH_SHORT).show()
            Toast.makeText(context, binding.weightSpinner.getSelectedObjectId(),Toast.LENGTH_SHORT).show()
            Toast.makeText(context, binding.educationSpinner.getSelectedObjectId(),Toast.LENGTH_SHORT).show()*/
            Toast.makeText(context, binding.employedCategorySpinner.getSelectedObjectId().safeGet(),Toast.LENGTH_SHORT).show()
            (requireActivity() as? CompleteRegistration)?.displayFragmentB()
        }
        subscribeData()
    }

    private fun subscribeData(){
        authViewModel.registrationFiltersOutput.observe(viewLifecycleOwner){
            binding.maritalRadioButtonList.submitList(it.marital_status as ArrayList<NameIdObject>)
            binding.complexionRadioButtonList.submitList(it.complexion as ArrayList<NameIdObject>)
            binding.bodyTypeRadioButtonList.submitList(it.body_type as ArrayList<NameIdObject>)
            binding.physicalStatusRadioButtonList.submitList(it.physical_status as ArrayList<NameIdObject>)
            binding.drinkingRadioButtonList.submitList(it.drinking_habit as ArrayList<NameIdObject>)
            binding.smokingRadioButtonList.submitList(it.smoking as ArrayList<NameIdObject>)
            binding.dietRadioButtonList.submitList(it.diet as ArrayList<NameIdObject>)

            context?.let { mContext ->
                val heightAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, (it.height as MutableList<NameIdObject>))
                binding.heightSpinner.adapter = heightAdapter


                val weightAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>( mContext, R.layout.simple_spinner_item, getAllWeights())
                binding.weightSpinner.adapter = weightAdapter

                val bloodGroupAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, it.blood_groups as MutableList<NameIdObject>)
                binding.bloodGroupSpinner.adapter = bloodGroupAdapter

                val educationAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, it.education_category as MutableList<NameIdObject>)
                binding.educationSpinner.adapter = educationAdapter

                val occupationAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, it.occupation_category as MutableList<NameIdObject>)
                binding.occupationSpinner.adapter = occupationAdapter

                val incomeAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, it.income_category as MutableList<NameIdObject>)
                binding.annualIncomeSpinner.adapter = incomeAdapter

                val employedAdapter: ArrayAdapter<NameIdObject> =
                    ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, it.employee_category as MutableList<NameIdObject>)
                binding.employedCategorySpinner.adapter = employedAdapter
            }
        }
    }

    private fun getAllWeights() : MutableList<NameIdObject>{
        val weightList = mutableListOf<NameIdObject>()
        for(i in 40 .. 100){
            val nameIdObject = NameIdObject()
            nameIdObject.id = i.toString()
            nameIdObject.name = "$i Kg"
            weightList.add(nameIdObject)
        }
        return weightList
    }

}