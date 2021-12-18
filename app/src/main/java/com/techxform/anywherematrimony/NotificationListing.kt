package com.techxform.anywherematrimony

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotificationListing : BaseActivity() {
    private val notificationListAdapter = CommonListAdapter()
    lateinit var profileListRv : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Notifications")
        val inflater : LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        inflater.inflate(R.layout.activity_notification_listing, frameContainer, true)
        profileListRv = findViewById(R.id.notification_list_rv)
        profileListRv.layoutManager = LinearLayoutManager(this)
        profileListRv.adapter = notificationListAdapter

        val itemDecoration = ItemOffsetDecoration(this, R.dimen.list_margin)
        profileListRv.addItemDecoration(itemDecoration)

        val list = ArrayList<String>()
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        list.add("Notification 1")
        notificationListAdapter.submitList(list)

    }


}