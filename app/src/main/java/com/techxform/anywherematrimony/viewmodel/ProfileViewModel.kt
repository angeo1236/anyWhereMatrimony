package com.techxform.anywherematrimony.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.BaseResultObject
import com.techxform.anywherematrimony.data.BaseResultObjectListData
import com.techxform.anywherematrimony.data.NotificationModel
import com.techxform.anywherematrimony.data.ViewProfileData
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import com.techxform.anywherematrimony.utils.DataCaching
import retrofit2.Call
import retrofit2.Callback

class ProfileViewModel(val dataCaching: DataCaching) : ViewModel() {

    private val _profileData = MutableLiveData<ViewProfileData>()
    val profileData: LiveData<ViewProfileData> = _profileData

    fun getSingleProfile(userId : String){

        val apiService = ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)

        val call = apiService?.getSingleProfile(userId)
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObject<ViewProfileData>> {
            override fun onResponse(
                call: Call<BaseResultObject<ViewProfileData>>,
                response: retrofit2.Response<BaseResultObject<ViewProfileData>>){
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _profileData.postValue(outputBean?.output)
                }else{
                    _profileData.postValue(null)
                }
            }
            override fun onFailure(
                call: Call<BaseResultObject<ViewProfileData>>,
                t: Throwable
            ) {
                _profileData.postValue(null)

            }
        })
    }

}