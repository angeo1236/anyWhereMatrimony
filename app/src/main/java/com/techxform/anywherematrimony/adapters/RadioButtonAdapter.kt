package com.techxform.anywherematrimony.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.ChipsModel
import com.techxform.anywherematrimony.data.NameIdObject
import com.techxform.anywherematrimony.data.NotificationModel
import java.util.ArrayList

class RadioButtonAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<NameIdObject>()

    fun submitList(arrayList: ArrayList<NameIdObject>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.radio_button_list_item, parent, false)

        return RadioButtonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = arrayList[position]
        if (holder is RadioButtonViewHolder){
            val context = holder.itemView.context
            holder.radioButton.text = item.name
            holder.radioButton.isChecked = item.selected

            holder.radioButton.setOnClickListener {
                resetAllSelected()
                item.selected = true
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    private fun resetAllSelected(){
        arrayList.map { it.selected = false }
    }
    class RadioButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var radioButton: AppCompatRadioButton = itemView.findViewById(R.id.radioButton)
    }
}