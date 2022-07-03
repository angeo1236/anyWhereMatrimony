package com.techxform.anywherematrimony.utils

import android.content.Context


private const val PREFERENCES = "com.techxform.anywherematrimony.helpers.DataCaching"

private const val ACCESS_TOKEN = "accessToken"
private const val USER_ID = "userId"
private const val EMAIL = "email"
private const val USER_NAME = "userName"
private const val USER_IMAGE = "userImage"
private const val RELIGION_ID = "religionId"

open class DataCaching constructor(var context: Context) {

    fun getAccessToken(): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return sp.getString(ACCESS_TOKEN, null)
    }

    fun setAccessToken(accessToken: String?) {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putString(ACCESS_TOKEN, accessToken).apply()
    }

    fun getUserId(): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return sp.getString(USER_ID, null)
    }

    fun setUserId(userId: String?) {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putString(USER_ID, userId).apply()
    }

    fun getEmail(): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return sp.getString(EMAIL, null)
    }

    fun setEmail(email: String?) {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putString(EMAIL, email).apply()
    }

    fun getUserName(): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return sp.getString(USER_NAME, null)
    }

    fun setUserName(userName: String?) {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putString(USER_NAME, userName).apply()
    }

    fun getUserImage(): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return sp.getString(USER_IMAGE, null)
    }

    fun setUserImage(userImage: String?) {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putString(USER_IMAGE, userImage).apply()
    }

    fun getReligionId(): String? {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        return sp.getString(RELIGION_ID, null)
    }

    fun setReligionId(religionId: String) {
        val sp = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sp.edit().putString(RELIGION_ID, religionId).apply()
    }

}