package com.techxform.anywherematrimony

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class CommonPairAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var arrayList = ArrayList<Pair<String, String>>()

    fun submitList(arrayList: ArrayList<Pair<String, String>>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.details_list_two_item, parent, false)

        return TwoLineViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pair = arrayList.get(position)
        if(holder is TwoLineViewHolder){
            holder.titleTv.text = pair.first
            holder.detailTv.text = pair.second

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class TwoLineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTv = itemView.findViewById<AppCompatTextView>(R.id.titleTv)
        var detailTv = itemView.findViewById<AppCompatTextView>(R.id.detailTv)
    }
}
