package com.techxform.anywherematrimony.data

import com.google.gson.annotations.SerializedName

data class HomePageOutput (
    @SerializedName("UserDetails")
    var userDetails: UserDetails?=null)

class UserDetails(
    var id : String?,
    var user_id : String?,
    var candidate_name : String?,
    var gender : String?,
    var age : String?,
    var height : String?,
    var weight : String?,
    var religion : String?,
    var caste : String?,
    var education : String?,
    var image : String?,
    var user_name : String?,
    var added_date : String?,
    var mobile : String?,
    var email : String?,
    var status : String?,
    var created_at : String?
)
/*
"UserDetails": {
    "id": "12",
    "user_id": "",
    "candidate_name": "sushama",
    "gender": "2",
    "age": "0",
    "height": "1",
    "weight": "55",
    "religion": "1",
    "caste": "2",
    "education": null,
    "image": "http://localhost/Anywhere_matrimony/assets/images/user2d244b86df197d994ea59638668bd553.jpg",
    "user_name": "deepa23",
    "added_date": "2021-12-11",
    "mobile": "9847563542",
    "email": "deepak@gmail.com",
    "status": "1",
    "created_at": "2021-12-11 15:00:18"
}*/
