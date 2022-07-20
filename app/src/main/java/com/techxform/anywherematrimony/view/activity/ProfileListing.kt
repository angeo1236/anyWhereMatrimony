package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.techxform.anywherematrimony.adapters.CommonListAdapter
import com.techxform.anywherematrimony.ItemOffsetDecoration
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.adapters.InterestsAdapter
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.databinding.ActivityProfileListingBinding
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.viewmodel.InterestViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileListing: BaseActivity() {

    private val interestViewModel: InterestViewModel by viewModel()
    private val interestListAdapter = InterestsAdapter()
    private lateinit var dataBinding: ActivityProfileListingBinding
    private var isWishList = false
    companion object{
        const val IS_IN_WISHLIST = "isWishList"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.activity_profile_listing, frameContainer, true)

        isWishList = intent.getBooleanExtra(IS_IN_WISHLIST, false)

        dataBinding.profileListRv.layoutManager = GridLayoutManager(this,2)
        dataBinding.profileListRv.adapter = interestListAdapter

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.list_margin)
        dataBinding.profileListRv.addItemDecoration(itemDecoration)

        initViews()

    }

    private fun initViews(){
        showProgress()
        if(isWishList.safeGet()){
            setTitle("Wishlist")
            dataBinding.headerLayout.visibility = View.GONE
            dataBinding.searchEdit.visibility = View.GONE
        }else{
            setTitle("Profile List")
        }
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