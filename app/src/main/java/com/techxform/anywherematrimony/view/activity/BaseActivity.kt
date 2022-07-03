package com.techxform.anywherematrimony.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.kaopiz.kprogresshud.KProgressHUD
import com.techxform.anywherematrimony.R

open class BaseActivity : AppCompatActivity() {
    lateinit var titleTv: AppCompatTextView
    lateinit var frameContainer: FrameLayout
    lateinit var notificationsIv: ImageFilterView
    lateinit var backIv: ImageFilterView
    lateinit var progressLoader: KProgressHUD
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

    open fun showProgress(detailText : String? = null){
       progressLoader = KProgressHUD.create(this@BaseActivity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setDetailsLabel(detailText?:"")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show();
    }

    open fun hideProgress(){
        if(this::progressLoader.isInitialized && progressLoader.isShowing){
            progressLoader.dismiss()
        }
    }
    fun setTitle(title: String) {
        titleTv.text = title
    }

}