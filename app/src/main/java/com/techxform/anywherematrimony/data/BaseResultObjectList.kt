package com.techxform.anywherematrimony.data

class BaseResultObjectList<T> {
    var status: Boolean = false
    var message: String? = null
    var dataList: List<T>? = null
}

class BaseResultObjectListData<T> {
    var status: Boolean = false
    var message: String? = null
    var data: List<T>? = null
}