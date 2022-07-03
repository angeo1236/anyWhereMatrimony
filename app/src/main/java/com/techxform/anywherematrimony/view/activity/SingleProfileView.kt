package com.techxform.anywherematrimony.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.techxform.anywherematrimony.CommonListAdapter
import com.techxform.anywherematrimony.CommonPairAdapter
import com.techxform.anywherematrimony.R

class SingleProfileView : AppCompatActivity() {
    lateinit var detailsRv: RecyclerView
    lateinit var religiousRv: RecyclerView
    lateinit var professionalRv: RecyclerView
    lateinit var lovedOnesRv: RecyclerView
    lateinit var hobbiesRv: RecyclerView
    lateinit var preferencesRv: RecyclerView
    lateinit var otherReligiousRv: RecyclerView
    lateinit var otherProfessionalRv: RecyclerView
    lateinit var locationRv: RecyclerView

    private val detailsAdapter = CommonListAdapter()
    private val religiousAdapter = CommonListAdapter()
    private val professionalAdapter = CommonListAdapter()
    private val lovedOnesAdapter = CommonListAdapter()
    private val hobbiesAdapter = CommonPairAdapter()
    private val preferencesAdapter = CommonPairAdapter()
    private val otherReligiousAdapter = CommonPairAdapter()
    private val otherProfessionalAdapter = CommonPairAdapter()
    private val locationAdapter = CommonPairAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_profile)
        detailsRv = findViewById(R.id.detailsRv)
        detailsRv.layoutManager = LinearLayoutManager(this)
        detailsRv.adapter = detailsAdapter
        detailsAdapter.setType("profile")
        val list = ArrayList<String>()
        list.add("Profile created by self")
        list.add("27yrs 3 months")
        list.add("60kg Slim")
        list.add("Never married")
        list.add("Mother tongue - Malayalam")
        detailsAdapter.submitList(list)

        religiousRv = findViewById(R.id.religiousRv)
        religiousRv.layoutManager = LinearLayoutManager(this)
        religiousRv.adapter = religiousAdapter
        religiousAdapter.setType("profile")

        val list1 = ArrayList<String>()
        list1.add("Hindu")
        list1.add("Nair")
        list1.add("Request star")
        list1.add("Request Horoscope")

        religiousAdapter.submitList(list1)


        professionalRv = findViewById(R.id.professionalRv)
        professionalRv.layoutManager = LinearLayoutManager(this)
        professionalRv.adapter = professionalAdapter
        professionalAdapter.setType("profile")

        val list2 = ArrayList<String>()
        list2.add("Employed in private sector")
        list2.add("Earns Rs.3 - 4 Lakhs annually")
        list2.add("Software professional")
        list2.add("B.tech")

        professionalAdapter.submitList(list2)

        lovedOnesRv = findViewById(R.id.lovedOnesRv)
        lovedOnesRv.layoutManager = LinearLayoutManager(this)
        lovedOnesRv.adapter = lovedOnesAdapter
        lovedOnesAdapter.setType("profile")

        val list3 = ArrayList<String>()
        list3.add("Nuclear middle class family with moderate values")
        list3.add("Request family details")

        lovedOnesAdapter.submitList(list3)

        hobbiesRv = findViewById(R.id.hobbiesRv)
        hobbiesRv.layoutManager = LinearLayoutManager(this)
        hobbiesRv.adapter = hobbiesAdapter

        val list4 = ArrayList<Pair<String,String>>()
        list4.add(Pair("Hobbies","Art / Handicraft"))
        list4.add(Pair("Interests","Music"))
        list4.add(Pair("Music Genre","Film songs"))
        list4.add(Pair("Sports/Fitness","Cricket"))

        hobbiesAdapter.submitList(list4)

        preferencesRv = findViewById(R.id.preferencesRv)
        preferencesRv.layoutManager = LinearLayoutManager(this)
        preferencesRv.adapter = preferencesAdapter

        val list5 = ArrayList<Pair<String,String>>()
        list5.add(Pair("Bride's Age ","26 - 30yrs"))
        list5.add(Pair("Height","5ft 3in - 5ft 9in"))
        list5.add(Pair("Marital status","Unmarried"))
        list5.add(Pair("Mother tongue","Malayalam"))
        list5.add(Pair("Physical status","Normal"))
        list5.add(Pair("Eating habits","Non-Vegetarian"))
        list5.add(Pair("Drinking habits","Doesn't matter"))

        preferencesAdapter.submitList(list5)


        otherReligiousRv = findViewById(R.id.otherReligiousRv)
        otherReligiousRv.layoutManager = LinearLayoutManager(this)
        otherReligiousRv.adapter = otherReligiousAdapter

        val list6 = ArrayList<Pair<String,String>>()
        list6.add(Pair("Religion","Hindu"))
        list6.add(Pair("Division","Nair"))
        list6.add(Pair("Caste","Any"))

        otherReligiousAdapter.submitList(list6)

        otherProfessionalRv = findViewById(R.id.otherProfessionalRv)
        otherProfessionalRv.layoutManager = LinearLayoutManager(this)
        otherProfessionalRv.adapter = otherProfessionalAdapter

        val list7 = ArrayList<Pair<String,String>>()
        list7.add(Pair("Education","Ph.D, ANy Post Graduation"))
        list7.add(Pair("Employed in","Any"))
        list7.add(Pair("Occupation","Any"))
        list7.add(Pair("Annual income","Any"))


        otherProfessionalAdapter.submitList(list6)


        locationRv = findViewById(R.id.locationRv)
        locationRv.layoutManager = LinearLayoutManager(this)
        locationRv.adapter = locationAdapter

        val list8 = ArrayList<Pair<String,String>>()
        list8.add(Pair("Country","Any"))
        list8.add(Pair("Residing State","Any"))
        list8.add(Pair("Residing City","Any"))
        list8.add(Pair("Citizenship","Any"))



        locationAdapter.submitList(list8)
    }
}