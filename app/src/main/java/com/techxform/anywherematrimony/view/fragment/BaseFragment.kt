package com.techxform.anywherematrimony.view.fragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.fragment.app.Fragment
import com.kaopiz.kprogresshud.KProgressHUD
import com.techxform.anywherematrimony.R

open class BaseFragment : Fragment() {

    lateinit var progressLoader: KProgressHUD

    open fun showProgress(detailText : String? = null){
       progressLoader = KProgressHUD.create(activity)
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


}