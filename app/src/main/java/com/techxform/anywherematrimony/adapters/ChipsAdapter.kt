package com.techxform.anywherematrimony.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.ChipsModel
import com.techxform.anywherematrimony.data.NotificationModel
import java.util.ArrayList

interface ChipsClickListener{
    fun onClickListener(statusId : Int?)
}

class ChipsAdapter(private val chipsClickListener: ChipsClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<ChipsModel>()
    fun submitList(arrayList: ArrayList<ChipsModel>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.chips_list_item, parent, false)

        return ChipViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = arrayList[position]
        if (holder is ChipViewHolder){
            val context = holder.itemView.context
            holder.chipNameTv.text = item.name
            if(item.isSelected){
                holder.chipNameTv.setTextColor(context.resources.getColor(R.color.white))
                holder.chipNameTv.setBackgroundResource(R.drawable.blue_bg_chips)
            }else{
                holder.chipNameTv.setBackgroundResource(R.drawable.grey_stroke_bg)
                holder.chipNameTv.setTextColor(context.resources.getColor(R.color.black))
            }
            holder.chipNameTv.setOnClickListener {
                resetAllSelected()
                item.isSelected = true
                notifyDataSetChanged()
                chipsClickListener.onClickListener(item.statusId)
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    private fun resetAllSelected(){
        arrayList.map { it.isSelected = false }
    }

    class ChipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var chipNameTv: AppCompatTextView = itemView.findViewById(R.id.chipNameTv)
    }
}