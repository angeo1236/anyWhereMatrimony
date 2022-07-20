package com.techxform.anywherematrimony.data

data class SignUpInput (
    var candidate_name : String? = null,
    var email : String? = null,
    var mobile : String? = null,
    var gender : String? = null,
    var religion : String? = null,
    var caste : String? = null,
    var username : String? = null,
    var password : String? = null,
    var profile_created_by : String? = null,
    var date_of_birth : String? = null,
    var country : String? = null,
    var state : String? = null,
    var district : String? = null,
    var place : String? = null
)

data class SignUpOutput(
    var otp : String? = null,
    var email : String? = null,
    var userid : String? = null
)

/*{
    "otp": 19675,
    "email": "maneesha",
    "userid": 8
}*/
/*
{
    "candidate_name": "maneesha",
    "email": "maneesha@gmail.com",
    "mobile": 9743760854,
    "gender": 1,
    "religion": 1,
    "caste": 2,
    "username": "maneesha",
    "password": "345343",
    "profile_created_by": 1,
    "date_of_birth": "2000-04-03",
    "country": 1,
    "state": 2,
    "district": 3,
    "place": 2
}*/
