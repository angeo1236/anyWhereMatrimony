package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.ItemOffsetDecoration
import com.techxform.anywherematrimony.adapters.NotificationAdapter
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.NotificationModel
import com.techxform.anywherematrimony.viewmodel.NotificationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationListing : BaseActivity() {
    private val notificationListAdapter = NotificationAdapter()
    lateinit var profileListRv : RecyclerView
    private val notificationViewModel: NotificationViewModel by viewModel()

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

        subscribeData()
        init()
    }

    private fun init(){
        showProgress()
        notificationViewModel.getNotifications()
    }

    private fun subscribeData(){
        notificationViewModel.notificationsList.observe(this) {
            hideProgress()
            it?.let {
               notificationListAdapter.submitList(it as ArrayList<NotificationModel>)
            }
        }
    }


}