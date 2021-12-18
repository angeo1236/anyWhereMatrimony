package com.techxform.anywherematrimony

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class CommonListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var arrayList = ArrayList<String>()

    fun submitList(arrayList: ArrayList<String>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            4->{
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_circle_layout, parent, false)

                ViewHolder(itemView)
            }  3->{
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.details_list_item, parent, false)

                ViewHolder(itemView)
            }
            2->{
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.notification_list_item, parent, false)

                ViewHolder(itemView)
            }
            else ->{
                val itemView: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.profile_list_item, parent, false)

                ProfileViewHolder(itemView)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is ProfileViewHolder){
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context,SingleProfileView::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        val string = arrayList[position]
        return when {
            string.startsWith("Notification") -> 2
            string.startsWith("detail") -> 3
            string.startsWith("home_bride") -> 4
            else -> 1
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }


    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
