package com.techxform.anywherematrimony.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.DataCaching

interface OnClickInterests{
    fun acceptInterest(userId : String)
    fun rejectInterest(userId: String)
}

class InterestsAdapter(private val dataCaching: DataCaching? = null,
                       private var onClickInterests: OnClickInterests
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var arrayList = ArrayList<InterestModel>()
    fun submitList(arrayList: ArrayList<InterestModel>) {
        this.arrayList = arrayList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.interest_grid_item, parent, false)

        return InterestViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is InterestViewHolder) {

            val item = arrayList[position]
            holder.nameTV.text = item.candidate_name.safeGet()

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.man_placeholder)
                .error(R.drawable.man_placeholder)

            Glide.with(holder.itemView.context).load(item.image).apply(options)
                .into(holder.profileIV)
            when (item.status.safeGet()) {
                "1" -> {
                    holder.acceptBtn.visibility = View.GONE
                    holder.rejectBtn.visibility = View.GONE
                    holder.statusTv.text = "ACCEPTED"
                    holder.statusTv.setTextColor(holder.itemView.context.resources.getColor(R.color.green))
                    holder.statusTv.visibility = View.VISIBLE
                }
                "2" -> {
                    holder.acceptBtn.visibility = View.GONE
                    holder.rejectBtn.visibility = View.GONE
                    holder.statusTv.text = "REJECTED"
                    holder.statusTv.setTextColor(holder.itemView.context.resources.getColor(R.color.dark_grey))
                    holder.statusTv.visibility = View.VISIBLE
                }
                "0" -> {
                    dataCaching?.let {
                        if (dataCaching.getUserId().equals(item.fromId)) {
                            holder.acceptBtn.visibility = View.GONE
                            holder.rejectBtn.visibility = View.GONE
                            holder.statusTv.setTextColor(holder.itemView.context.resources.getColor(R.color.blue))
                            holder.statusTv.text = "PENDING"
                            holder.statusTv.visibility = View.VISIBLE
                        } else {
                            holder.acceptBtn.visibility = View.VISIBLE
                            holder.rejectBtn.visibility = View.VISIBLE
                            holder.statusTv.visibility = View.GONE
                        }
                    } ?: run {
                        holder.acceptBtn.visibility = View.VISIBLE
                        holder.rejectBtn.visibility = View.VISIBLE
                        holder.statusTv.visibility = View.GONE
                    }
                }
            }

            holder.acceptBtn.setOnClickListener{
                onClickInterests.acceptInterest(item.fromId.safeGet())
            }

            holder.rejectBtn.setOnClickListener{
                onClickInterests.rejectInterest(item.fromId.safeGet())
            }
        }
    }


    override fun getItemCount(): Int {
        return arrayList.size
    }


    class InterestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTV: AppCompatTextView = itemView.findViewById(R.id.nameTV)
        var profileIV: ImageView = itemView.findViewById(R.id.profileIV)
        var acceptBtn: TextView = itemView.findViewById(R.id.acceptBtn)
        var rejectBtn: TextView = itemView.findViewById(R.id.rejectBtn)
        var statusTv: TextView = itemView.findViewById(R.id.statusTv)
    }
}