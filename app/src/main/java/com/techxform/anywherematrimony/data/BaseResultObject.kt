package com.techxform.anywherematrimony.data

class BaseResultObject<T> {
    var status: Boolean = false
    var message: String? = null
    var output: T? = null
}