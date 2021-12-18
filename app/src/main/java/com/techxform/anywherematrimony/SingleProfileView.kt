package com.techxform.anywherematrimony

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SingleProfileView : AppCompatActivity() {
    lateinit var detailsRv : RecyclerView
    private val detailsAdapter = CommonListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_profile)
        detailsRv = findViewById(R.id.detailsRv)
        detailsRv.layoutManager = LinearLayoutManager(this)
        detailsRv.adapter = detailsAdapter

        val list = ArrayList<String>()
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        list.add("detail 1")
        detailsAdapter.submitList(list)
    }
}