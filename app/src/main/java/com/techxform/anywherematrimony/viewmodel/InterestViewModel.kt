package com.techxform.anywherematrimony.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.*
import com.techxform.anywherematrimony.extensions.empty
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import com.techxform.anywherematrimony.utils.DataCaching
import retrofit2.Call
import retrofit2.Callback

class InterestViewModel(val dataCaching: DataCaching) : ViewModel() {
    private val _interestList = MutableLiveData<List<InterestModel>>()
    val interestList: LiveData<List<InterestModel>> = _interestList

    private val _sentInterest = MutableLiveData<Boolean>()
    val sentInterest: LiveData<Boolean> = _sentInterest

    private val _acceptRejectInterest = MutableLiveData<Boolean>()
    val acceptRejectInterest: LiveData<Boolean> = _acceptRejectInterest

    fun getInterests(status: Int? = null) {

        val apiService =
            ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)
        val statusMap = mutableMapOf<String, String>()
        var call = apiService?.getInterests(statusMap)

        status?.let {
            statusMap[STATUS_KEY_MAP] = status.toString()
            call = apiService?.getInterests(statusMap)
        } ?: run {
            call = apiService?.getSentInterests()
        }
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
                } else {
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

    fun sendInterest(userId: String) {

        val apiService =
            ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)
        val userIdMap = mutableMapOf<String, String>()
        userIdMap[USERID_MAP] = userId
        val call = apiService?.sendInterest(userIdMap)
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObject<String>> {
            override fun onResponse(
                call: Call<BaseResultObject<String>>,
                response: retrofit2.Response<BaseResultObject<String>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _sentInterest.postValue(outputBean?.status)
                } else {
                    _sentInterest.postValue(false)
                }
            }

            override fun onFailure(
                call: Call<BaseResultObject<String>>,
                t: Throwable
            ) {
                _sentInterest.postValue(false)

            }
        })
    }

   fun acceptRejectInterest(userId: String, acceptRejectStatus: String) {

        val apiService =
            ApiClient.getClientWithAuthorization(dataCaching)?.create(ApiInterface::class.java)
        val userIdMap = mutableMapOf<String, String>()
        userIdMap[USERID_MAP] = userId
        userIdMap[STATUS_KEY_MAP] = acceptRejectStatus
        val call = apiService?.acceptRejectResponse(userIdMap)
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObject<String>> {
            override fun onResponse(
                call: Call<BaseResultObject<String>>,
                response: retrofit2.Response<BaseResultObject<String>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _acceptRejectInterest.postValue(outputBean?.status)
                } else {
                    _acceptRejectInterest.postValue(false)
                }
            }

            override fun onFailure(
                call: Call<BaseResultObject<String>>,
                t: Throwable
            ) {
                _acceptRejectInterest.postValue(false)

            }
        })
    }

    companion object {
        const val STATUS_KEY_MAP = "status"
        const val USERID_MAP = "userId"
    }
}