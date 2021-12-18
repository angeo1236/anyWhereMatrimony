package com.techxform.anywherematrimony

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ProfileListing : BaseActivity() {
    private val profileListAdapter = CommonListAdapter()
    lateinit var profileListRv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Profile List")
        val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.activity_profile_listing, frameContainer, true)
        profileListRv = findViewById(R.id.profile_list_rv)
        profileListRv.layoutManager = GridLayoutManager(this,2)
        profileListRv.adapter = profileListAdapter

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.list_margin)
        profileListRv.addItemDecoration(itemDecoration)

        val list = ArrayList<String>()
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        list.add("1")
        profileListAdapter.submitList(list)

    }
}