package com.techxform.anywherematrimony.view.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.databinding.ActivityWebviewPageBinding

class WebviewPage : BaseActivity() {
    private lateinit var databinding: ActivityWebviewPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        databinding = DataBindingUtil.inflate(inflater,R.layout.activity_webview_page,frameContainer,true)

        databinding.webView.webViewClient = WebViewClient()
        // this will enable the javascript settings
        databinding.webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        databinding.webView.settings.setSupportZoom(true)
        openUrl()
    }

    private fun openUrl(){
        val url = "https://nearu.live/Anywhere_matrimony/magazine/view"
        databinding.webView.loadUrl(url)
        databinding.webView.requestFocus()
    }
}