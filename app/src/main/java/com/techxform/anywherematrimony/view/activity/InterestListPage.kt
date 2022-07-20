package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.adapters.ChipsAdapter
import com.techxform.anywherematrimony.adapters.CommonListAdapter
import com.techxform.anywherematrimony.adapters.InterestsAdapter
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.databinding.ActivityInterestListPageBinding
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.viewmodel.InterestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class InterestListPage : BaseActivity() {
    private lateinit var dataBinding: ActivityInterestListPageBinding
    private var chipsAdapter = ChipsAdapter()
    private val interestViewModel: InterestViewModel by viewModel()
    private val interestListAdapter = InterestsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Interests")

        val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.activity_interest_list_page, frameContainer, true)


        initViews()
    }

    private fun initViews(){
        showProgress()
        dataBinding.selectionRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        dataBinding.selectionRv.adapter = chipsAdapter

        val chipsList = mutableListOf<String>("All","Pending","Sent","Received","Cancelled","Wishlist")
        chipsAdapter.submitList(chipsList as ArrayList<String>)

        dataBinding.profilesRv.layoutManager = GridLayoutManager(this,2)
        dataBinding.profilesRv.adapter = interestListAdapter
        interestViewModel.getInterests()
        subscribeData()
    }

    private fun subscribeData(){
        interestViewModel.interestList.observe(this) {
            hideProgress()
            it?.let {
                interestListAdapter.submitList(it as ArrayList<InterestModel>)
            }
        }
    }

}