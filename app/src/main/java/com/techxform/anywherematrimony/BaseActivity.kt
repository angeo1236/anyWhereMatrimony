package com.techxform.anywherematrimony

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView

open class BaseActivity : AppCompatActivity() {
    lateinit var titleTv: AppCompatTextView
    lateinit var frameContainer: FrameLayout
    lateinit var notificationsIv: ImageFilterView
    lateinit var backIv: ImageFilterView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        titleTv = findViewById(R.id.titleTv)
        frameContainer = findViewById(R.id.container)
        notificationsIv = findViewById(R.id.notificationsIv)
        backIv = findViewById(R.id.backIv)

        notificationsIv.setOnClickListener {
            val intent = Intent(this, NotificationListing::class.java)
            startActivity(intent)
        }
        backIv.setOnClickListener {
           onBackPressed()
        }

    }

    fun setTitle(title: String) {
        titleTv.text = title
    }

}