package com.techxform.anywherematrimony.data

class RegistrationFiltersOutput {
    var status: Boolean = false
    var message: String? = null
    var gender : List<NameIdObject>? = null
    var country : List<NameIdObject>? = null
    var profile_created_by : List<NameIdObject>? = null
    var family_status : List<NameIdObject>? = null
    var income_category : List<NameIdObject>? = null
    var employee_category : List<NameIdObject>? = null
    var occupation_category : List<NameIdObject>? = null
    var education_category : List<NameIdObject>? = null
    var smoking : List<NameIdObject>? = null
    var diet : List<NameIdObject>? = null
    var blood_groups : List<NameIdObject>? = null
    var complexion : List<NameIdObject>? = null
    var drinking_habit : List<NameIdObject>? = null
    var about_templates : List<NameIdObject>? = null
    var physical_status : List<NameIdObject>? = null
    var body_type : List<NameIdObject>? = null
    var marital_status : List<NameIdObject>? = null
    var religion : List<NameIdObject>? = null
    var star : List<NameIdObject>? = null
    var height : List<NameIdObject>? = null
}
/*
{
    "status": true,
    "message": "Success",
    "gender": [
    {
        "id": "1",
        "name": "Male",
        "created_at": "2021-12-07 17:23:14"
    },
    {
        "id": "2",
        "name": "Female",
        "created_at": "2021-12-07 17:23:14"
    }
    ],
    "country": [
    {
        "id": "1",
        "iso": "AF",
        "nickName": "AFGHANISTAN",
        "name": "Afghanistan",
        "iso3": "AFG",
        "numcode": "4",
        "phonecode": "93"
    },
    {
        "id": "2",
        "iso": "AL",
        "nickName": "ALBANIA",
        "name": "Albania",
        "iso3": "ALB",
        "numcode": "8",
        "phonecode": "355"
    }
    ],
    "profile_created_by": [
    {
        "id": "1",
        "name": "Self",
        "created_at": "2021-12-07 11:27:26"
    },
    {
        "id": "2",
        "name": "Father",
        "created_at": "2021-12-07 11:27:26"
    },
    {
        "id": "3",
        "name": "Mother",
        "created_at": "2021-12-07 11:27:26"
    },
    {
        "id": "4",
        "name": "Sister",
        "created_at": "2021-12-07 11:27:26"
    },
    {
        "id": "5",
        "name": "Brother",
        "created_at": "2021-12-07 11:27:26"
    }
    ],
    "family_status": [
    {
        "id": "1",
        "name": "Rich",
        "created_at": "2021-12-04 16:17:05"
    },
    {
        "id": "2",
        "name": "Upper Middle Class",
        "created_at": "2021-12-04 16:17:05"
    },
    {
        "id": "3",
        "name": "Middle Class",
        "created_at": "2021-12-04 16:17:05"
    },
    {
        "id": "4",
        "name": "Lower Middle Class",
        "created_at": "2021-12-04 16:17:05"
    }
    ],
    "income_category": [
    {
        "id": "1",
        "name": "Upto INR 1 lakhs",
        "created_at": "2021-12-04 16:00:00"
    },
    {
        "id": "2",
        "name": "INR 1 lakhs - 2 lakhs",
        "created_at": "2021-12-04 16:00:00"
    },
    {
        "id": "3",
        "name": "INR 2 lakhs - 5 lakhs",
        "created_at": "2021-12-04 16:00:00"
    }
    ],
    "employee_category": [
    {
        "id": "1",
        "name": "Central Gvt",
        "created_at": "0000-00-00 00:00:00"
    },
    {
        "id": "2",
        "name": "State Gvt",
        "created_at": "0000-00-00 00:00:00"
    },
    {
        "id": "3",
        "name": "Private",
        "created_at": "0000-00-00 00:00:00"
    },
    {
        "id": "4",
        "name": "Abroad",
        "created_at": "2022-03-29 18:20:40"
    }
    ],
    "occupation_category": [
    {
        "id": "1",
        "name": "Manager",
        "created_at": "2021-12-04 15:39:04"
    },
    {
        "id": "2",
        "name": "Clerk",
        "created_at": "2021-12-04 15:39:04"
    },
    {
        "id": "3",
        "name": "Executive",
        "created_at": "2021-12-04 15:39:04"
    }
    ],
    "education_category": [
    {
        "id": "1",
        "name": "Aeronautical Engineering",
        "created_at": "2021-12-04 15:34:28"
    },
    {
        "id": "2",
        "name": "B.Arch",
        "created_at": "2021-12-04 15:34:28"
    },
    {
        "id": "3",
        "name": "BCA",
        "created_at": "2021-12-04 15:34:28"
    },
    {
        "id": "4",
        "name": "BE",
        "created_at": "2021-12-04 15:34:28"
    }
    ],
    "smoking": [
    {
        "id": "1",
        "name": "Non Smoker",
        "created_at": "2021-12-04 15:19:56"
    },
    {
        "id": "2",
        "name": "Occational Smoker",
        "created_at": "2021-12-04 15:19:56"
    },
    {
        "id": "3",
        "name": "Regular Smoker",
        "created_at": "2021-12-04 15:19:56"
    }
    ],
    "diet": [
    {
        "id": "1",
        "name": "Vegetarian",
        "created_at": "2021-12-04 14:29:02"
    },
    {
        "id": "2",
        "name": "Non Vegerarian",
        "created_at": "2021-12-04 14:29:02"
    },
    {
        "id": "3",
        "name": "vegan",
        "created_at": "2021-12-04 14:29:02"
    }
    ],
    "blood_groups": [
    {
        "id": "1",
        "name": "O+",
        "created_at": "2021-04-26 10:23:23"
    },
    {
        "id": "2",
        "name": "O-",
        "created_at": "2021-04-26 10:23:23"
    },
    {
        "id": "3",
        "name": "A+",
        "created_at": "2021-04-26 10:24:32"
    },
    {
        "id": "4",
        "name": "A-",
        "created_at": "2021-04-26 10:24:32"
    },
    {
        "id": "5",
        "name": "B+",
        "created_at": "2021-04-26 10:24:43"
    },
    {
        "id": "6",
        "name": "B-",
        "created_at": "2021-04-26 10:24:43"
    },
    {
        "id": "7",
        "name": "AB+",
        "created_at": "2021-04-26 10:25:11"
    },
    {
        "id": "8",
        "name": "AB-",
        "created_at": "2021-04-26 10:25:11"
    }
    ],
    "complexion": [
    {
        "id": "1",
        "name": "very fair",
        "created_at": "2021-12-04 14:22:56"
    },
    {
        "id": "2",
        "name": "fair",
        "created_at": "2021-12-04 14:22:56"
    },
    {
        "id": "3",
        "name": "moderate fair",
        "created_at": "2021-12-04 14:22:56"
    },
    {
        "id": "4",
        "name": "medium",
        "created_at": "2021-12-04 14:22:56"
    },
    {
        "id": "5",
        "name": "dark",
        "created_at": "2021-12-04 14:22:56"
    }
    ],
    "drinking_habit": [
    {
        "id": "1",
        "name": "Teetotaller",
        "created_at": "2021-12-04 13:10:25"
    },
    {
        "id": "2",
        "name": "Occational Drinker",
        "created_at": "2021-12-04 13:10:25"
    },
    {
        "id": "3",
        "name": "Regular Drinker",
        "created_at": "2021-12-04 13:10:25"
    }
    ],
    "about_templates": [
    {
        "id": "1",
        "content": "This is the sample text 1"
    },
    {
        "id": "2",
        "content": "This is the sample text 2"
    }
    ],
    "physical_status": [
    {
        "id": "1",
        "name": "Normal",
        "created_at": "2021-12-04 13:01:36"
    },
    {
        "id": "2",
        "name": "Disabled",
        "created_at": "2021-12-04 13:01:36"
    }
    ],
    "body_type": [
    {
        "id": "1",
        "name": "Heavy",
        "created_at": "2021-12-01 08:41:07"
    },
    {
        "id": "2",
        "name": "Average",
        "created_at": "2021-12-01 08:41:07"
    },
    {
        "id": "3",
        "name": "Athletic",
        "created_at": "2021-12-01 08:41:07"
    },
    {
        "id": "4",
        "name": "Slim",
        "created_at": "2021-12-01 08:41:07"
    }
    ],
    "marital_status": [
    {
        "id": "1",
        "name": "UnMarried",
        "created_at": "2021-12-01 13:06:03"
    },
    {
        "id": "2",
        "name": "Widow/Widower",
        "created_at": "2021-12-01 13:06:03"
    },
    {
        "id": "3",
        "name": "Divorced",
        "created_at": "2021-12-01 13:06:03"
    },
    {
        "id": "4",
        "name": "Awaiting Divorce",
        "created_at": "2021-12-01 13:06:03"
    }
    ],
    "religion": [
    {
        "id": "1",
        "name": "christian",
        "table_name": "",
        "status": "0"
    },
    {
        "id": "2",
        "name": "hindu",
        "table_name": "",
        "status": "0"
    },
    {
        "id": "3",
        "name": "muslim",
        "table_name": "",
        "status": "0"
    }
    ],
    "star": [
    {
        "id": "1",
        "name": "Aswathi",
        "created_at": "2022-01-04 11:36:50"
    },
    {
        "id": "2",
        "name": "Bharani",
        "created_at": "2022-01-04 11:36:50"
    }
    ],
    "height": [
    {
        "id": "1",
        "name": "4ft - 121cm",
        "created_at": "2021-12-01 11:10:12"
    },
    {
        "id": "2",
        "name": "4ft 1in - 124cm",
        "created_at": "2021-12-01 11:10:12"
    }
    ]
}*/
