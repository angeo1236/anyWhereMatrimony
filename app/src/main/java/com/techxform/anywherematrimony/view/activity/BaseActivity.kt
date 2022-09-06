package com.techxform.anywherematrimony.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.kaopiz.kprogresshud.KProgressHUD
import com.techxform.anywherematrimony.R
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

open class BaseActivity(var showActionBar: Boolean = false) : AppCompatActivity() {
    lateinit var titleTv: AppCompatTextView
    lateinit var frameContainer: FrameLayout
    lateinit var notificationsIv: ImageFilterView
    lateinit var backIv: ImageFilterView
    lateinit var lottieProgress: LottieAnimationView
    lateinit var lottieLayout: LinearLayout
    lateinit var headerLayout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        headerLayout = findViewById(R.id.headerLayout)
        titleTv = findViewById(R.id.titleTv)
        frameContainer = findViewById(R.id.container)
        notificationsIv = findViewById(R.id.notificationsIv)
        backIv = findViewById(R.id.backIv)
        lottieProgress = findViewById(R.id.lottieProgress)
        lottieLayout = findViewById(R.id.lottieLayout)

        if (showActionBar){
            headerLayout.visibility = View.VISIBLE
        }else{
            headerLayout.visibility = View.GONE
        }
        notificationsIv.setOnClickListener {
            val intent = Intent(this, NotificationListing::class.java)
            startActivity(intent)
        }
        backIv.setOnClickListener {
           onBackPressed()
        }

    }

    open fun showProgress(detailText : String? = null){

        lottieLayout.visibility = View.VISIBLE
        lottieProgress.playAnimation()
     /*  progressLoader = KProgressHUD.create(this@BaseActivity)
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel("Please wait")
            .setDetailsLabel(detailText?:"")
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
            .show();*/
    }

    open fun hideProgress(){
            lottieProgress.cancelAnimation()
            lottieLayout.visibility = View.GONE

       /* if(this::progressLoader.isInitialized && progressLoader.isShowing){
            progressLoader.dismiss()
        }*/
    }
    fun setTitle(title: String) {
        titleTv.text = title
    }

}