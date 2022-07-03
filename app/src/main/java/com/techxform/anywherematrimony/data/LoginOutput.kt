package com.techxform.anywherematrimony.data

data class LoginOutput (
    var token : String? = null,
    var user_id : String? = null,
    var image : String? = null,
    var email : String? = null,
    var user_name : String? = null
)

/*
{
    "token": "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjQiLCJuYW1lIjoidmFydW4iLCJ1c2VybmFtZSI6InZhcnVuMTIzNEBnbWFpbC5jb20iLCJpYXQiOjE2Mzk4MTE1MDMsImV4cCI6MTYzOTk4NDMwM30.VI-SwWS5qI88mV2yxk3OuMF4atYrHv8jxF4_IBoHTlM",
    "user_id": "4",
    "image": "http://localhost/Anywhere_matrimony/assets/images/user/2d244b86df197d994ea59638668bd553.jpg",
    "email": "varun1234@gmail.com",
    "user_name": "varun"
}*/
