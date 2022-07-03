package com.techxform.anywherematrimony.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.databinding.ActivityHomePageBinding
import org.koin.android.ext.android.inject
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions
import com.techxform.anywherematrimony.*
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.HomePageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePage : BaseActivity() {
    lateinit var roundFaceRecyclerview : RecyclerView
    lateinit var myMatchesRv : RecyclerView
    lateinit var recentlyAddedRv : RecyclerView
    lateinit var bottomMatchesRv : RecyclerView

    lateinit var myMatchesSeeAllTv : AppCompatTextView
    lateinit var recentlyAddedSeeAllTv : AppCompatTextView
    lateinit var bottomMatchesSeeAllTv : AppCompatTextView
    lateinit var notification_iv : ImageView
    private val homePageViewModel: HomePageViewModel by viewModel()

    lateinit var searchEdit : EditText

    var profileListAdapter = CommonListAdapter()
    var myMatchesAdapter = CommonListAdapter()
    var recentlyAddedAdapter = CommonListAdapter()
    var bottomMatchesAdapter = CommonListAdapter()
    private lateinit var databinding: ActivityHomePageBinding
    private val dataCaching: DataCaching by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)


        populateUserData()
        homePageViewModel.getHomePageData()

        roundFaceRecyclerview = findViewById(R.id.roundFaceRecyclerview)
        myMatchesRv = findViewById(R.id.myMatchesRv)
        recentlyAddedRv = findViewById(R.id.recentlyAddedRv)
        bottomMatchesRv = findViewById(R.id.bottomMatchesRv)
        notification_iv = findViewById(R.id.notification_iv)

        myMatchesSeeAllTv = findViewById(R.id.myMatchesSeeAllTv)
        recentlyAddedSeeAllTv = findViewById(R.id.recentlyAddedSeeAllTv)
        bottomMatchesSeeAllTv = findViewById(R.id.bottomMatchesSeeAllTv)

        searchEdit = findViewById(R.id.searchEdit)
        notification_iv = findViewById(R.id.notification_iv)

        roundFaceRecyclerview.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        roundFaceRecyclerview.adapter = profileListAdapter

        myMatchesRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        myMatchesRv.adapter = myMatchesAdapter


        recentlyAddedRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        recentlyAddedRv.adapter = recentlyAddedAdapter


        bottomMatchesRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        bottomMatchesRv.adapter = bottomMatchesAdapter

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.list_margin)
        myMatchesRv.addItemDecoration(itemDecoration)
        recentlyAddedRv.addItemDecoration(itemDecoration)
        bottomMatchesRv.addItemDecoration(itemDecoration)

        val list = ArrayList<String>()
        list.add("home_bride1")
        list.add("home_bride1")
        list.add("home_bride1")
        list.add("home_bride1")
        list.add("home_bride1")
        list.add("home_bride1")
        list.add("home_bride1")
        list.add("home_bride1")
        profileListAdapter.submitList(list)

        val list1 = ArrayList<String>()
        list1.add("1")
        list1.add("1")
        list1.add("1")
        list1.add("1")
        list1.add("1")
        list1.add("1")
        list1.add("1")
        myMatchesAdapter.submitList(list1)


        val list2 = ArrayList<String>()
        list2.add("long1")
        list2.add("long1")
        list2.add("long1")
        list2.add("long1")
        list2.add("long1")
        list2.add("long1")
        list2.add("long1")

        recentlyAddedAdapter.submitList(list2)


        val list3 = ArrayList<String>()
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")
        list3.add("grid1")



        bottomMatchesAdapter.submitList(list3)

        recentlyAddedSeeAllTv.setOnClickListener { goToProfileListing() }
        myMatchesSeeAllTv.setOnClickListener { goToProfileListing() }
        bottomMatchesSeeAllTv.setOnClickListener { goToProfileListing() }
        searchEdit.setOnClickListener { goToProfileListing() }
        notification_iv.setOnClickListener {
            val intent = Intent(this, NotificationListing::class.java)
            startActivity(intent) }
        subscribeData()

    }

    private fun subscribeData(){
        homePageViewModel.homePageOutput.observe(this) {
            dataCaching.setUserName(it.userDetails?.candidate_name.safeGet())
            dataCaching.setEmail(it.userDetails?.email.safeGet())
            dataCaching.setUserImage(it.userDetails?.image.safeGet())
            populateUserData()
        }
    }

    private fun populateUserData(){
        databinding.userNameTv.text = dataCaching.getUserName()?.uppercase()
        "ID : USER000${dataCaching.getUserId()}".also { databinding.userIdTv.text = it }
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.man_placeholder)
            .error(R.drawable.man_placeholder)

        Glide.with(this).load(dataCaching.getUserImage()).apply(options)
            .into(databinding.profileIv)
    }

    private fun goToProfileListing(){
        val intent = Intent(this, ProfileListing::class.java)
        startActivity(intent)
    }
}