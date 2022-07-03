package com.techxform.anywherematrimony.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.ReligionSelectionAdapter
import com.techxform.anywherematrimony.databinding.ActivityHomePageBinding
import com.techxform.anywherematrimony.databinding.ActivityReligionSelectionBinding
import com.techxform.anywherematrimony.databinding.ReligionSelectionListItemBinding
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import org.koin.android.ext.android.inject

class ReligionSelection : AppCompatActivity() {
    private lateinit var databinding: ActivityReligionSelectionBinding
    private var religionSelectionAdapter = ReligionSelectionAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_religion_selection)

        val arrayList = ArrayList<Pair<String,String>>()
        arrayList.add(Pair("Login with\nanyhindu matrimony","2"))
        arrayList.add(Pair("Login with\nanychristian matrimony","1"))
        arrayList.add(Pair("Login with\nanymuslim matrimony","3"))


        databinding.religionRv.layoutManager = LinearLayoutManager(this)
        databinding.religionRv.adapter = religionSelectionAdapter

        religionSelectionAdapter.submitList(arrayList)
    }
}