package com.techxform.anywherematrimony

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.utils.AppPreferences
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.view.activity.HomePage
import com.techxform.anywherematrimony.view.activity.MainActivity
import com.techxform.anywherematrimony.view.activity.SingleProfileView
import java.util.ArrayList

class ReligionSelectionAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<Pair<String,String>>()

    fun submitList(arrayList: ArrayList<Pair<String,String>>) {
        this.arrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.religion_selection_list_item, parent, false)

        return ReligionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReligionViewHolder){
            holder.religionLoginTv.text = arrayList[position].first
            holder.itemView.setOnClickListener {
                AppPreferences.religionId = arrayList[position].second
                val intent = Intent(holder.itemView.context, MainActivity::class.java)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class ReligionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var religionLoginTv = itemView.findViewById<AppCompatTextView>(R.id.religionLoginTv)
    }
}