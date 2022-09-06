package com.techxform.anywherematrimony.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.*
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import com.techxform.anywherematrimony.utils.AppPreferences
import com.techxform.anywherematrimony.utils.DataCaching
import retrofit2.Call
import retrofit2.Callback

class HomePageViewModel(val dataCaching: DataCaching) : ViewModel() {

    private val _homePageOutput = MutableLiveData<HomePageOutput>()
    val homePageOutput: LiveData<HomePageOutput> = _homePageOutput

    private val _profileList = MutableLiveData<List<ProfileModel>>()
    val profileList: LiveData<List<ProfileModel>> = _profileList

    fun getHomePageData() {

        val apiService =
            ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)

        val call = apiService?.getHomePage()
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObject<HomePageOutput>> {
            override fun onResponse(
                call: Call<BaseResultObject<HomePageOutput>>,
                response: retrofit2.Response<BaseResultObject<HomePageOutput>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _homePageOutput.postValue(outputBean?.output)
                } else {
                    _homePageOutput.postValue(HomePageOutput(null))
                }
            }

            override fun onFailure(
                call: Call<BaseResultObject<HomePageOutput>>,
                t: Throwable
            ) {
                _homePageOutput.postValue(HomePageOutput(null))

            }
        })
    }

    fun getAllProfiles() {

        val apiService =
            ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)

        val call = apiService?.getProfileList()
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObjectList<ProfileModel>> {
            override fun onResponse(
                call: Call<BaseResultObjectList<ProfileModel>>,
                response: retrofit2.Response<BaseResultObjectList<ProfileModel>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _profileList.postValue(outputBean?.dataList)
                } else {
                    _profileList.postValue(null)
                }
            }

            override fun onFailure(
                call: Call<BaseResultObjectList<ProfileModel>>,
                t: Throwable
            ) {
                _profileList.postValue(null)

            }
        })
    }

}