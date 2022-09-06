package com.techxform.anywherematrimony.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.NotificationModel
import com.techxform.anywherematrimony.data.ProfileModel
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.AppPreferences
import com.techxform.anywherematrimony.view.activity.MainActivity
import com.techxform.anywherematrimony.view.activity.SingleProfileView
import java.util.ArrayList

enum class ProfileListingType{
    GRID_ITEM, LONG_ITEM, CIRCLE_ITEM
}

class ProfilesListAdapter(private val profileType : ProfileListingType) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<ProfileModel>()

    fun submitList(arrayList: ArrayList<ProfileModel>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       when(profileType){
           ProfileListingType.LONG_ITEM -> {
               val itemView: View = LayoutInflater.from(parent.context)
                   .inflate(R.layout.long_profile_layout, parent, false)

               return ProfileLongViewHolder(itemView)
           }
           ProfileListingType.GRID_ITEM -> {
               val itemView: View = LayoutInflater.from(parent.context)
                   .inflate(R.layout.profile_grid_item, parent, false)

               return ProfileGridViewHolder(itemView)
           }
           else -> {
               val itemView: View = LayoutInflater.from(parent.context)
                   .inflate(R.layout.long_profile_layout, parent, false)

               return ProfileGridViewHolder(itemView)
           }

       }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = holder.itemView.context
        if (holder is ProfileLongViewHolder){
            val item = arrayList[position]
            holder.nameTV.text = item.candidate_name.safeGet()
            holder.idTV.text = item.id.safeGet()
            holder.educationalTV.text = item.education

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.man_placeholder)
                .error(R.drawable.man_placeholder)

            Glide.with(holder.itemView.context).load(item.image).apply(options)
                .into(holder.profileIV)

        } else if (holder is ProfileGridViewHolder){
            val item = arrayList[position]
            holder.nameTV.text = item.candidate_name.safeGet()
            holder.idTV.text = item.id.safeGet()
            holder.educationalTV.text = item.education

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.man_placeholder)
                .error(R.drawable.man_placeholder)

            Glide.with(holder.itemView.context).load(item.image).apply(options)
                .into(holder.profileIV)

        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context,SingleProfileView::class.java)
            intent.putExtra("profile",arrayList[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    class ProfileLongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: AppCompatTextView = itemView.findViewById(R.id.nameTV)
        var idTV: AppCompatTextView = itemView.findViewById(R.id.idTV)
        var educationalTV: AppCompatTextView = itemView.findViewById(R.id.educationalTV)
        var profileIV: ImageView = itemView.findViewById(R.id.profileIV)
    }

    class ProfileGridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: AppCompatTextView = itemView.findViewById(R.id.nameTV)
        var idTV: AppCompatTextView = itemView.findViewById(R.id.idTV)
        var educationalTV: AppCompatTextView = itemView.findViewById(R.id.educationalTV)
        var profileIV: ImageView = itemView.findViewById(R.id.profileIV)
    }
}