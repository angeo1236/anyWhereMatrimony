package com.techxform.anywherematrimony

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.data.NotificationModel
import com.techxform.anywherematrimony.utils.AppPreferences
import com.techxform.anywherematrimony.view.activity.MainActivity
import java.util.ArrayList

class NotificationAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<NotificationModel>()

    fun submitList(arrayList: ArrayList<NotificationModel>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_list_item, parent, false)

        return NotificationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is NotificationViewHolder){
            val item = arrayList[position]
            holder.nameTV.text = item.name
            holder.notificationLine1Tv.text = item.description
            holder.dateTV.text = item.created_at

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: AppCompatTextView = itemView.findViewById(R.id.nameTV)
        var notificationLine1Tv: AppCompatTextView = itemView.findViewById(R.id.notificationLine1Tv)
        var dateTV: AppCompatTextView = itemView.findViewById(R.id.dateTV)
    }
}