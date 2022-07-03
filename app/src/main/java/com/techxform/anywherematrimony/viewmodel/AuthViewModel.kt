package com.techxform.anywherematrimony.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techxform.anywherematrimony.data.BaseResultObject
import com.techxform.anywherematrimony.data.LoginOutput
import com.techxform.anywherematrimony.network.ApiClient
import com.techxform.anywherematrimony.network.ApiInterface
import com.techxform.anywherematrimony.utils.AppPreferences
import retrofit2.Call
import retrofit2.Callback

class AuthViewModel() : ViewModel() {

    private val _loginOutput = MutableLiveData<LoginOutput>()
    val loginOutput: LiveData<LoginOutput> = _loginOutput


    fun checkLogin(email: String, password: String){
        val loginInput = mutableMapOf<String,String>()
        loginInput["name"] = email
        loginInput["password"] = password
        AppPreferences.religionId?.let {
            loginInput["religion"] = it
        }?: return

        val apiService = ApiClient.client?.create(ApiInterface::class.java)

        val call = apiService?.signInUser(loginInput)
        val URL = call?.request()?.url.toString()
        println("Retrofit URL : $URL")
        call?.enqueue(object : Callback<BaseResultObject<LoginOutput>> {
            override fun onResponse(
                call: Call<BaseResultObject<LoginOutput>>,
                response: retrofit2.Response<BaseResultObject<LoginOutput>>
            ) {
                if (response.code() == 200) {
                    val outputBean = response.body()
                    _loginOutput.postValue(outputBean?.output)
                }else{
                    _loginOutput.postValue(LoginOutput(user_id = null))
                }
            }
            override fun onFailure(
                call: Call<BaseResultObject<LoginOutput>>,
                t: Throwable
            ) {
                _loginOutput.postValue(LoginOutput(user_id = null))

            }
        })
    }
}