package com.techxform.anywherematrimony.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.BaseResultObjectList
import com.techxform.anywherematrimony.data.BaseResultObjectListData
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.data.NotificationModel
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import com.techxform.anywherematrimony.utils.DataCaching
import retrofit2.Call
import retrofit2.Callback

class InterestViewModel(val dataCaching: DataCaching) : ViewModel() {
    private val _interestList = MutableLiveData<List<InterestModel>>()
    val interestList: LiveData<List<InterestModel>> = _interestList

    fun getInterests(){

        val apiService = ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)

        val call = apiService?.getInterests()
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObjectList<InterestModel>> {
            override fun onResponse(
                call: Call<BaseResultObjectList<InterestModel>>,
                response: retrofit2.Response<BaseResultObjectList<InterestModel>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _interestList.postValue(outputBean?.dataList)
                }else{
                    _interestList.postValue(listOf())
                }
            }
            override fun onFailure(
                call: Call<BaseResultObjectList<InterestModel>>,
                t: Throwable
            ) {
                _interestList.postValue(listOf())

            }
        })
    }
}