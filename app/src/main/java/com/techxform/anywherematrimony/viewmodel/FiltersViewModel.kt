package com.techxform.anywherematrimony.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.BaseResultObject
import com.techxform.anywherematrimony.data.BaseResultObjectList
import com.techxform.anywherematrimony.data.FilterItem
import com.techxform.anywherematrimony.data.LoginOutput
import com.techxform.anywherematrimony.helpers.event.Event
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import org.koin.core.logger.Logger
import retrofit2.Call
import retrofit2.Callback

class FiltersViewModel() : ViewModel() {

    private val LOG_TAG = javaClass.name


    private val _genderList = MutableLiveData<List<FilterItem>>()
     val genderList : LiveData<List<FilterItem>> = _genderList

   private val _countryList = MutableLiveData<Event<List<FilterItem>>>()
     val countryList : LiveData<Event<List<FilterItem>>> = _countryList

   private val _profileCreatedList = MutableLiveData<Event<List<FilterItem>>>()
     val profileCreatedList : LiveData<Event<List<FilterItem>>> = _profileCreatedList

   private val _religionList = MutableLiveData<Event<List<FilterItem>>>()
     val religionList : LiveData<Event<List<FilterItem>>> = _religionList

   private val _castList = MutableLiveData<Event<List<FilterItem>>>()
     val castList : LiveData<Event<List<FilterItem>>> = _castList

   private val _stateList = MutableLiveData<Event<List<FilterItem>>>()
     val stateList : LiveData<Event<List<FilterItem>>> = _stateList

   private val _districtList = MutableLiveData<Event<List<FilterItem>>>()
     val districtList : LiveData<Event<List<FilterItem>>> = _districtList

  private val _cityList = MutableLiveData<Event<List<FilterItem>>>()
     val cityList : LiveData<Event<List<FilterItem>>> = _cityList


    fun getFilters(filterType: String){

        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        val call = apiService?.getFilters(filterType)
        val url = call?.request()?.url.toString()
        println("Retrofit URL : $url")
        call?.enqueue(object : Callback<BaseResultObjectList<FilterItem>> {
            override fun onResponse(
                call: Call<BaseResultObjectList<FilterItem>>,
                response: retrofit2.Response<BaseResultObjectList<FilterItem>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    val sortedList = ArrayList<FilterItem>()
                    sortedList.add(FilterItem(null,"Select"))
                    outputBean?.dataList?.let { sortedList.addAll(it) }
                    when(filterType){
                        GENDER_FILTER -> _genderList.postValue(sortedList)
                        COUNTRY_FILTER -> _countryList.postValue(Event(sortedList))
                        PROFILE_CREATED_FILTER -> _profileCreatedList.postValue(Event(sortedList))
                        RELIGION_FILTER -> _religionList.postValue(Event(sortedList))
                    }
                }else{
                    Log.d(LOG_TAG,"Api failed")
                }
            }
            override fun onFailure(
                call: Call<BaseResultObjectList<FilterItem>>,
                t: Throwable
            ) {
                Log.d(LOG_TAG,"Api failed")
            }
        })
    }

    fun getFilters(filterType: String, argumentId : String){

        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        val call = apiService?.getFiltersWithCategory(filterType, argumentId)
        val url = call?.request()?.url.toString()
        println("Retrofit URL : $url")
        call?.enqueue(object : Callback<BaseResultObjectList<FilterItem>> {
            override fun onResponse(
                call: Call<BaseResultObjectList<FilterItem>>,
                response: retrofit2.Response<BaseResultObjectList<FilterItem>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    val sortedList = ArrayList<FilterItem>()
                    sortedList.add(FilterItem(null,"Select"))
                    outputBean?.dataList?.let { sortedList.addAll(it) }
                    when(filterType){
                        CAST_FILTER -> _castList.postValue(Event(sortedList))
                        STATE_FILTER -> _stateList.postValue(Event(sortedList))
                        DISTRICT_FILTER -> _districtList.postValue(Event(sortedList))
                        PLACES_FILTER -> _cityList.postValue(Event(sortedList))
                    }
                }else{
                    Log.d(LOG_TAG,"Api failed")
                }
            }
            override fun onFailure(
                call: Call<BaseResultObjectList<FilterItem>>,
                t: Throwable
            ) {
                Log.d(LOG_TAG,"Api failed")
            }
        })
    }

    companion object{
        const val GENDER_FILTER = "gender"
        const val COUNTRY_FILTER = "country"
        const val PROFILE_CREATED_FILTER = "profile_created_by"
        const val RELIGION_FILTER = "religion"
        const val CAST_FILTER = "cast"
        const val STATE_FILTER = "state"
        const val DISTRICT_FILTER = "district"
        const val PLACES_FILTER = "city"
    }
}