package com.techxform.anywherematrimony.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.BaseResultObject
import com.techxform.anywherematrimony.data.BaseResultObjectListData
import com.techxform.anywherematrimony.data.HomePageOutput
import com.techxform.anywherematrimony.data.NotificationModel
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import com.techxform.anywherematrimony.utils.DataCaching
import retrofit2.Call
import retrofit2.Callback

class NotificationViewModel(val dataCaching: DataCaching) : ViewModel() {

    private val _notificationsList = MutableLiveData<List<NotificationModel>>()
    val notificationsList: LiveData<List<NotificationModel>> = _notificationsList

    fun getNotifications(){

        val apiService = ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)

        val call = apiService?.getAllNotifications()
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObjectListData<NotificationModel>> {
            override fun onResponse(
                call: Call<BaseResultObjectListData<NotificationModel>>,
                response: retrofit2.Response<BaseResultObjectListData<NotificationModel>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _notificationsList.postValue(outputBean?.data)
                }else{
                    _notificationsList.postValue(emptyList())
                }
            }
            override fun onFailure(
                call: Call<BaseResultObjectListData<NotificationModel>>,
                t: Throwable
            ) {
                _notificationsList.postValue(emptyList())

            }
        })
    }

}