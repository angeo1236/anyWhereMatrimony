package com.techxform.anywherematrimony.application

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.techxform.anywherematrimony.utils.AppPreferences
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import com.techxform.anywherematrimony.viewmodel.HomePageViewModel
import com.techxform.anywherematrimony.viewmodel.NotificationViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AnyWhereApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@AnyWhereApp)
            modules(
                listOf(
                    authModule
                )
            )
        }
        AppPreferences.init(applicationContext)
        startLogging()
    }
}
private fun startLogging() {
    val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
        .showThreadInfo(false)
        .methodCount(0)
        .methodOffset(7)
        .tag("ANYWHERE_LOG")
        .build()
    Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
        override fun isLoggable(priority: Int, tag: String?): Boolean {
//            return BuildConfig.DEBUG
            return true
        }
    })
}

val authModule = module {
    single { DataCaching( androidApplication()) }

    viewModel { AuthViewModel() }
    viewModel { FiltersViewModel() }
    viewModel { HomePageViewModel(get()) }
    viewModel { NotificationViewModel(get()) }

}