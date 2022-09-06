package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.adapters.ChipsAdapter
import com.techxform.anywherematrimony.adapters.ChipsClickListener
import com.techxform.anywherematrimony.adapters.InterestsAdapter
import com.techxform.anywherematrimony.adapters.OnClickInterests
import com.techxform.anywherematrimony.data.ChipsModel
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.databinding.ActivityInterestListPageBinding
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.viewmodel.InterestViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class InterestListPage : BaseActivity(true), ChipsClickListener, OnClickInterests {
    private lateinit var dataBinding: ActivityInterestListPageBinding
    private var chipsAdapter = ChipsAdapter(this)
    private val interestViewModel: InterestViewModel by viewModel()
    private val dataCaching: DataCaching by inject()
    private val interestListAdapter = InterestsAdapter(dataCaching,this)
    private var currentStatusId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Interests")

        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.activity_interest_list_page,frameContainer,true)
        initViews()
    }

    private fun initViews() {
        showProgress()
        dataBinding.selectionRv.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        dataBinding.selectionRv.adapter = chipsAdapter

        val chipsList = mutableListOf(
            ChipsModel("Sent", null, true),
            ChipsModel("Accepted", 1, false),
            ChipsModel("Rejected", 2, false),
            ChipsModel("Pending", 0, false)
        )

        chipsAdapter.submitList(chipsList as ArrayList<ChipsModel>)

        dataBinding.profilesRv.layoutManager = GridLayoutManager(this, 2)
        dataBinding.profilesRv.adapter = interestListAdapter
        interestViewModel.getInterests(null)
        subscribeData()
    }

    private fun subscribeData() {
        interestViewModel.interestList.observe(this) {
            hideProgress()
            if (it.isNullOrEmpty().not()) {
                interestListAdapter.submitList(it as ArrayList<InterestModel>)
            }
        }

        interestViewModel.acceptRejectInterest.observe(this) {
            hideProgress()
            if (it.safeGet()) {
                showProgress()
                interestViewModel.getInterests(currentStatusId)
            }
        }
    }

    override fun onClickListener(statusId: Int?) {
        showProgress()
        interestListAdapter.submitList(ArrayList())
        currentStatusId = statusId
        interestViewModel.getInterests(statusId)
    }

    override fun acceptInterest(userId: String) {
        showProgress()
        interestListAdapter.submitList(ArrayList())
        interestViewModel.acceptRejectInterest(userId, "1")
    }

    override fun rejectInterest(userId: String) {
        showProgress()
        interestListAdapter.submitList(ArrayList())
        interestViewModel.acceptRejectInterest(userId, "2")
    }

}