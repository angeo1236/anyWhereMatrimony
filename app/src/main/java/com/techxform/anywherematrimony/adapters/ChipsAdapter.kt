package com.techxform.anywherematrimony.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.NotificationModel
import java.util.ArrayList

class ChipsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<String>()
    var selectedPosition = 0
    fun submitList(arrayList: ArrayList<String>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chips_list_item, parent, false)

        return ChipViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ChipViewHolder){
            val item = arrayList[position]
            holder.chipNameTv.text = item
            holder.chipNameTv.isPressed = selectedPosition == position
            holder.chipNameTv.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class ChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chipNameTv: AppCompatTextView = itemView.findViewById(R.id.chipNameTv)
    }
}