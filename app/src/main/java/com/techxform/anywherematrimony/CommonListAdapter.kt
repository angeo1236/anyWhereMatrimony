package com.techxform.anywherematrimony

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.view.activity.SingleProfileView
import java.util.ArrayList

class CommonListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var arrayList = ArrayList<String>()
    private var type : String ="common"
    fun submitList(arrayList: ArrayList<String>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    fun setType(type : String){
        this.type = type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            6 -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_grid_item, parent, false)

                ViewHolder(itemView)
            }
            5 -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.long_profile_layout, parent, false)

                ViewHolder(itemView)
            }
            4 -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_circle_layout, parent, false)

                ViewHolder(itemView)
            }
            3 -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.details_list_item, parent, false)

                ProfileDetailViewHolder(itemView)
            }
            2 -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.notification_list_item, parent, false)

                ViewHolder(itemView)
            }
            else -> {
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_list_item, parent, false)

                ProfileViewHolder(itemView)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ProfileViewHolder) {
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, SingleProfileView::class.java)
                holder.itemView.context.startActivity(intent)
            }
        } else
            if (holder is ProfileDetailViewHolder) {
                holder.detailTv.setText(arrayList.get(position))
            }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        val string = arrayList[position]
        return when {
            type == "profile" -> 3
            string.startsWith("Notification") -> 2
            string.startsWith("detail") -> 3
            string.startsWith("home_bride") -> 4
            string.startsWith("long") -> 5
            string.startsWith("grid") -> 6
            else -> 1
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class ProfileDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var detailTv = itemView.findViewById<AppCompatTextView>(R.id.detailTv)
    }
}
