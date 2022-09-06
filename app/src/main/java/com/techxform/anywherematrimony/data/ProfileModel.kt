package com.techxform.anywherematrimony.data

import java.io.Serializable

class ProfileModel (
    var id : String? = null,
    var candidate_name : String? = null,
    var gender : String? = null,
    var age : String? = null,
    var height : String? = null,
    var weight : String? = null,
    var religion : String? = null,
    var caste : String? = null,
    var education : String? = null,
    var image : String? = null,
    var date_of_birth : String? = null,
    var willing_to_second_marriage : String? = null,
    var status : String? = null,
    var created_at : String? = null,
    var admin_login_key : String? = null,
    var wishlistStatus : String? = null,
): Serializable

/*
{
    "id": "42",
    "candidate_name": "Arjun",
    "gender": "1",
    "age": "25",
    "height": "6",
    "weight": "65",
    "religion": "2",
    "caste": "24",
    "education": null,
    "image": "https://nearu.live/Anywhere_matrimony/uploads/profile/58c5a86e1240ef6ab496f42c79d5b433.png",
    "date_of_birth": "0000-00-00",
    "willing_to_second_marriage": "0",
    "status": "1",
    "created_at": "2022-07-01 07:03:36",
    "admin_login_key": "",
    "wishlistStatus": "Inactive"
}*/
/*
{
    "id": "18",
    "candidate_name": "Rakesh",
    "gender": "Male",
    "age": "0",
    "height": "5ft 6in - 167cm",
    "weight": "79",
    "religion": "hindu",
    "caste": "Nair",
    "education": null,
    "image": "",
    "willing_to_second_marriage": "0",
    "status": "Active",
    "admin_login_key": "",
    "approved_or_rejected_all_images": "0",
    "images_updated_at": null,
    "complexion": "fair",
    "marital_status": "UnMarried",
    "education_category": "BCA",
    "working_place": "Thrissur",
    "wishlistStatus": "Inactive"
}*/
