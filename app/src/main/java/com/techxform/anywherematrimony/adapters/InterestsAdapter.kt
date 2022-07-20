package com.techxform.anywherematrimony.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.extensions.safeGet

class InterestsAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<InterestModel>()

    fun submitList(arrayList: ArrayList<InterestModel>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.profile_grid_item, parent, false)

        return InterestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InterestViewHolder){

            val item = arrayList[position]
            holder.nameTV.text = item.candidate_name.safeGet()
            holder.idTV.text = item.id.safeGet()
            holder.educationalTV.text = item.message

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.man_placeholder)
                .error(R.drawable.man_placeholder)

            Glide.with(holder.itemView.context).load(item.image).apply(options)
                .into(holder.profileIV)

        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class InterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: AppCompatTextView = itemView.findViewById(R.id.nameTV)
        var idTV: AppCompatTextView = itemView.findViewById(R.id.idTV)
        var educationalTV: AppCompatTextView = itemView.findViewById(R.id.educationalTV)
        var profileIV: ImageView = itemView.findViewById(R.id.profileIV)
    }
}