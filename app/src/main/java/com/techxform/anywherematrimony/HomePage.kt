package com.techxform.anywherematrimony

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomePage : AppCompatActivity() {
    lateinit var roundFaceRecyclerview : RecyclerView
    lateinit var myMatchesRv : RecyclerView
    lateinit var recentlyAddedRv : RecyclerView

    lateinit var myMatchesSeeAllTv : AppCompatTextView
    lateinit var recentlyAddedSeeAllTv : AppCompatTextView

    lateinit var searchEdit : EditText

    var profileListAdapter = CommonListAdapter()
    var myMatchesAdapter = CommonListAdapter()
    var recentlyAddedAdapter = CommonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        roundFaceRecyclerview = findViewById(R.id.roundFaceRecyclerview)
        myMatchesRv = findViewById(R.id.myMatchesRv)
        recentlyAddedRv = findViewById(R.id.recentlyAddedRv)

        myMatchesSeeAllTv = findViewById(R.id.myMatchesSeeAllTv)
        recentlyAddedSeeAllTv = findViewById(R.id.recentlyAddedSeeAllTv)

        searchEdit = findViewById(R.id.searchEdit)

        roundFaceRecyclerview.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        roundFaceRecyclerview.adapter = profileListAdapter

        myMatchesRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        myMatchesRv.adapter = myMatchesAdapter


        recentlyAddedRv.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        recentlyAddedRv.adapter = recentlyAddedAdapter

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.list_margin)
        myMatchesRv.addItemDecoration(itemDecoration)
        recentlyAddedRv.addItemDecoration(itemDecoration)

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

        recentlyAddedAdapter.submitList(list1)

        recentlyAddedSeeAllTv.setOnClickListener { goToProfileListing() }
        myMatchesSeeAllTv.setOnClickListener { goToProfileListing() }

    }
    private fun goToProfileListing(){
        val intent = Intent(this, ProfileListing::class.java)
        startActivity(intent)
    }
}