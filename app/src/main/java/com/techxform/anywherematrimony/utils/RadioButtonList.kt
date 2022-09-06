package com.techxform.anywherematrimony.utils

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.adapters.RadioButtonAdapter
import com.techxform.anywherematrimony.data.NameIdObject
import org.koin.core.KoinComponent
import org.koin.core.inject

class RadioButtonList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle),
    KoinComponent {
    private val radioButtonAdapter by inject<RadioButtonAdapter>()

    init {
        initViews()
    }

    private fun initViews() {
        layoutManager = GridLayoutManager(context, 2)
        setHasFixedSize(false)
        adapter = radioButtonAdapter
    }

    fun build() {

    }

    fun submitList(filters: ArrayList<NameIdObject>) {
        radioButtonAdapter.submitList(filters)

    }
}