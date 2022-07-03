package com.techxform.anywherematrimony.data

data class FilterItem (
    var id : String? = null,
    var name : String? = null,
    var created_by : String? = null,
    var religion : String? = null,
    var state_title : String? = null,
    var district_title : String? = null
)
{
    override fun toString(): String {
        return name?:created_by?:religion?:state_title?:district_title?:""
    }
}
/*{"id":"1","name":"Male","created_at":"2021-12-07 17:23:14"}*/
