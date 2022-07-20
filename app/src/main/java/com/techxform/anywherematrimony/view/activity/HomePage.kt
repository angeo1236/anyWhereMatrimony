package com.techxform.anywherematrimony.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techxform.anywherematrimony.*
import com.techxform.anywherematrimony.adapters.CommonListAdapter
import com.techxform.anywherematrimony.adapters.NavListAdapter
import com.techxform.anywherematrimony.adapters.ProfileListingType
import com.techxform.anywherematrimony.adapters.ProfilesListAdapter
import com.techxform.anywherematrimony.data.NavListModel
import com.techxform.anywherematrimony.data.ProfileModel
import com.techxform.anywherematrimony.databinding.BaseNavigationLayoutBinding
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.viewmodel.HomePageViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomePage : BaseActivity() {

    private val homePageViewModel: HomePageViewModel by viewModel()
    private var profileListAdapter = CommonListAdapter()
    private var myMatchesAdapter = ProfilesListAdapter(ProfileListingType.GRID_ITEM)
    private var recentlyAddedAdapter = ProfilesListAdapter(ProfileListingType.LONG_ITEM)
    private var bottomMatchesAdapter = CommonListAdapter()
    private lateinit var dataBinding: BaseNavigationLayoutBinding
    private val dataCaching: DataCaching by inject()
    var navListModels = ArrayList<NavListModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.base_navigation_layout)

        populateUserData()
        homePageViewModel.getHomePageData()

        dataBinding.homePageLayout.roundFaceRecyclerview.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        dataBinding.homePageLayout.roundFaceRecyclerview.adapter = profileListAdapter

        dataBinding.homePageLayout.myMatchesRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        dataBinding.homePageLayout.myMatchesRv.adapter = myMatchesAdapter


        dataBinding.homePageLayout.recentlyAddedRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        dataBinding.homePageLayout.recentlyAddedRv.adapter = recentlyAddedAdapter


        dataBinding.homePageLayout.bottomMatchesRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        dataBinding.homePageLayout.bottomMatchesRv.adapter = bottomMatchesAdapter

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.list_margin)
        dataBinding.homePageLayout.myMatchesRv.addItemDecoration(itemDecoration)
        dataBinding.homePageLayout.recentlyAddedRv.addItemDecoration(itemDecoration)
        dataBinding.homePageLayout.bottomMatchesRv.addItemDecoration(itemDecoration)

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

        dataBinding.homePageLayout.recentlyAddedSeeAllTv.setOnClickListener { goToProfileListing() }
        dataBinding.homePageLayout.myMatchesSeeAllTv.setOnClickListener { goToProfileListing() }
        dataBinding.homePageLayout.bottomMatchesSeeAllTv.setOnClickListener { goToProfileListing() }
        dataBinding.homePageLayout.searchEdit.setOnClickListener { goToProfileListing() }
        dataBinding.homePageLayout.notificationIv.setOnClickListener {
            val intent = Intent(this, NotificationListing::class.java)
            startActivity(intent) }
        subscribeData()
        setNavigationMenuList()
        dataBinding.homePageLayout.navbarIv.setOnClickListener {
            dataBinding.drawerLayout.openDrawer(GravityCompat.START)
        }

    }

    private fun subscribeData(){
        homePageViewModel.homePageOutput.observe(this) {
            dataCaching.setUserName(it.userDetails?.candidate_name.safeGet())
            dataCaching.setEmail(it.userDetails?.email.safeGet())
            dataCaching.setUserImage(it.userDetails?.image.safeGet())
            (it?.recentProfiles as? java.util.ArrayList<ProfileModel>)?.let{ recentProfilesAll ->
                recentlyAddedAdapter.submitList(recentProfilesAll)
            }

            (it?.matchingProfiles as? java.util.ArrayList<ProfileModel>)?.let{ myMatches ->
                myMatchesAdapter.submitList(myMatches)
            }
            populateUserData()
        }
    }

    private fun populateUserData(){
        dataBinding.homePageLayout.userNameTv.text = dataCaching.getUserName()?.uppercase()
        "ID : USER000${dataCaching.getUserId()}".also { dataBinding.homePageLayout.userIdTv.text = it }
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.man_placeholder)
            .error(R.drawable.man_placeholder)

        Glide.with(this).load(dataCaching.getUserImage()).apply(options)
            .into(dataBinding.homePageLayout.profileIv)
    }

    private fun goToProfileListing(){
        val intent = Intent(this, ProfileListing::class.java)
        startActivity(intent)
    }

    private fun setNavigationMenuList(){

        navListModels.add(NavListModel("My Matches", R.drawable.like_icon))
        navListModels.add(NavListModel("Interest Message", R.drawable.like_icon))
        navListModels.add(NavListModel("Wishlist", R.drawable.like_icon))
        navListModels.add(NavListModel("Profile settings", R.drawable.like_icon))
        navListModels.add(NavListModel("Upload photos", R.drawable.like_icon))
        navListModels.add(NavListModel("Logout", R.drawable.like_icon))


        val navListAdapter =
            NavListAdapter(
                navListModels,
                this,
                dataBinding.drawerLayout
            )
        val mLayoutManager1: RecyclerView.LayoutManager =
            LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        dataBinding.navmenuRecyclerview.layoutManager = mLayoutManager1
        dataBinding.navmenuRecyclerview.adapter = navListAdapter
    }
}