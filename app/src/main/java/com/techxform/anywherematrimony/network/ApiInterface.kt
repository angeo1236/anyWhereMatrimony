package com.techxform.anywherematrimony.network

import com.techxform.anywherematrimony.data.*
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("Auth/login")
    fun signInUser(@Body userCredentials: Map<String,String>): Call<BaseResultObject<LoginOutput>>

    @POST("Auth/signup")
    fun signUpUser(@Body signUpInput: SignUpInput): Call<BaseResultObject<SignUpOutput>>

    @GET("Common/get_list/{filterType}")
    fun getFilters(@Path("filterType") filterType: String): Call<BaseResultObjectList<FilterItem>>

    @GET("Common/get_subcategory/{subcategory_name}/{category_id}")
    fun getFiltersWithCategory(@Path("subcategory_name") subcategory_name: String, @Path("category_id") category_id: String): Call<BaseResultObjectList<FilterItem>>

    @POST("homepage")
    fun getHomePage(): Call<BaseResultObject<HomePageOutput>>

    @POST("notification/get_all")
    fun getAllNotifications(): Call<BaseResultObjectListData<NotificationModel>>

    @GET("user/interest")
    fun getInterests(): Call<BaseResultObjectList<InterestModel>>

}