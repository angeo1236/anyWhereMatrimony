package com.techxform.anywherematrimony.network

import com.techxform.anywherematrimony.data.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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

    @GET("user/interest_status")
    fun getSentInterests(): Call<BaseResultObjectList<InterestModel>>

    @POST("user/interest")
    fun getInterests(@Body status: Map<String,String>): Call<BaseResultObjectList<InterestModel>>

    @POST("user/sent_interest")
    fun sendInterest(@Body userId: Map<String,String>): Call<BaseResultObject<String>>

    @POST("user/interest_response")
    fun acceptRejectResponse(@Body userAcceptReject: Map<String,String>): Call<BaseResultObject<String>>

    @POST("Search/homepage_search")
    fun getProfileList(): Call<BaseResultObjectList<ProfileModel>>

    @GET("user/view_profile/{userId}")
    fun getSingleProfile(@Path("userId") userId: String): Call<BaseResultObject<ViewProfileData>>

    @GET("Common/get_list_single")
    fun getRegistrationFilters(): Call<RegistrationFiltersOutput>

    @Multipart
    @POST("auth/User_registration")
    fun registerUser(@PartMap  params : Map<String, RequestBody>) : Call<ResponseBody>

    @Multipart
    @POST("auth/User_registration")
    fun registerUser(@Part("marital_status") marital_status: RequestBody,
                     @Part("willing_to_second_marriage") willing_to_second_marriage: RequestBody,
                     @Part("complexion") complexion: RequestBody,
                     @Part("body_type") body_type: RequestBody,
                     @Part("physical_status") physical_status: RequestBody,
                     @Part("drinking") drinking: RequestBody,
                     @Part("smoking") smoking: RequestBody,
                     @Part("diet") diet: RequestBody,
                     @Part("hobbies") hobbies: RequestBody,
                     @Part("height") height: RequestBody,
                     @Part("weight") weight: RequestBody,
                     @Part("blood_group") blood_group: RequestBody,
                     @Part("address") address: RequestBody,
                     @Part("pincode") pincode: RequestBody,
                     @Part("alternate_mobile") alternate_mobile: RequestBody,
                     @Part("education_category") education_category: RequestBody,
                     @Part("occupation_category") occupation_category: RequestBody,
                     @Part("working_firm") working_firm: RequestBody,
                     @Part("working_place") working_place: RequestBody,
                     @Part("income_category") income_category: RequestBody,
                     @Part("employed_category") employed_category: RequestBody,
                     @Part("family_name") family_name: RequestBody,
                     @Part("family_status") family_status: RequestBody,
                     @Part("father_name") father_name: RequestBody,
                     @Part("father_housename") father_housename: RequestBody,
                     @Part("father_occupation") father_occupation: RequestBody,
                     @Part("mother_name") mother_name: RequestBody,
                     @Part("mother_housename") mother_housename: RequestBody,
                     @Part("mother_occupation") mother_occupation: RequestBody,
                     @Part("married_sis") married_sis: RequestBody,
                     @Part("unmarried_sis") unmarried_sis: RequestBody,
                     @Part("unmarried_bros") unmarried_bros: RequestBody,
                     @Part("married_bors") married_bors: RequestBody,
                     @Part("reference_name") reference_name: RequestBody,
                     @Part("referece_relation") referece_relation: RequestBody,
                     @Part("reference_phone") reference_phone: RequestBody,
                     @Part("star") star: RequestBody,
                     @Part("horoscope_type") horoscope_type: RequestBody,
                     @Part horoscope: MultipartBody.Part,
                     @Part id_document1: MultipartBody.Part,
                     @Part id_document2: MultipartBody.Part,
                     @Part("about_candidate") about_candidate: RequestBody,
                     @Part("creator_name") creator_name: RequestBody,
                     @Part("creator_phone") creator_phone: RequestBody,
                     @Part("land_line_no") land_line_no: RequestBody,
                     @Part("relation_to_candidate") relation_to_candidate: RequestBody,
                     @Part("instagram") instagram: RequestBody,
                     @Part("facebook") facebook: RequestBody,
                     @Part("twitter") twitter: RequestBody,
                     @Part("linkedin") linkedin: RequestBody,
                     @Part("partnerPreference") partnerPreference: RequestBody): Call<BaseResultObject<String>>

}