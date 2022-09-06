package com.techxform.anywherematrimony.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techxform.anywherematrimony.adapters.CommonListAdapter
import com.techxform.anywherematrimony.adapters.CommonPairAdapter
import com.techxform.anywherematrimony.R
import com.techxform.anywherematrimony.data.InterestModel
import com.techxform.anywherematrimony.data.ProfileModel
import com.techxform.anywherematrimony.databinding.ActivitySingleProfileBinding
import com.techxform.anywherematrimony.databinding.BaseNavigationLayoutBinding
import com.techxform.anywherematrimony.extensions.addIfNotNull
import com.techxform.anywherematrimony.extensions.empty
import com.techxform.anywherematrimony.extensions.safeGet
import com.techxform.anywherematrimony.utils.DataCaching
import com.techxform.anywherematrimony.viewmodel.InterestViewModel
import com.techxform.anywherematrimony.viewmodel.ProfileViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SingleProfileView : BaseActivity() {
    private lateinit var dataBinding: ActivitySingleProfileBinding
    private val interestViewModel: InterestViewModel by viewModel()
    private val profileViewModel: ProfileViewModel by viewModel()

    private val detailsAdapter = CommonListAdapter()
    private val religiousAdapter = CommonPairAdapter()
    private val professionalAdapter = CommonListAdapter()
    private val lovedOnesAdapter = CommonListAdapter()
    private val hobbiesAdapter = CommonPairAdapter()
    private val preferencesAdapter = CommonPairAdapter()
    private val otherReligiousAdapter = CommonPairAdapter()
    private val otherProfessionalAdapter = CommonPairAdapter()
    private val locationAdapter = CommonPairAdapter()
    private var profileModel: ProfileModel? = null
    private val dataCaching: DataCaching by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater: LayoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.activity_single_profile,frameContainer,true)

        profileModel = intent.getSerializableExtra("profile") as? ProfileModel

        initViews()
    }

    private fun initViews() {
        profileModel?.let { profile ->
            showProgress()
            profileViewModel.getSingleProfile(profile.id.safeGet())
            setImage(dataCaching.getGenderId().safeGet(), dataCaching.getUserImage().safeGet(), dataBinding.myPhotoImageView)
            dataBinding.candidateNameTv.text = profile.candidate_name.safeGet()
            dataBinding.collapsingToolbar.title = profile.candidate_name.safeGet()
            dataBinding.normalToolbar.title = profile.candidate_name.safeGet()

            dataBinding.sendInterestBtn.setOnClickListener {
                showProgress("Sending interest")
                interestViewModel.sendInterest(profile.id.safeGet())
            }

            subscribeData()
        }

        dataBinding.detailsRv.layoutManager = LinearLayoutManager(this)
        detailsAdapter.setType("profile")
        dataBinding.detailsRv.adapter = detailsAdapter

        dataBinding.religiousRv.layoutManager = LinearLayoutManager(this)
        dataBinding.religiousRv.adapter = religiousAdapter




        dataBinding.professionalRv.layoutManager = LinearLayoutManager(this)
        dataBinding.professionalRv.adapter = professionalAdapter
        professionalAdapter.setType("profile")

        val list2 = ArrayList<String>()
        list2.add("Employed in private sector")
        list2.add("Earns Rs.3 - 4 Lakhs annually")
        list2.add("Software professional")
        list2.add("B.tech")

        professionalAdapter.submitList(list2)

        dataBinding.lovedOnesRv.layoutManager = LinearLayoutManager(this)
        dataBinding.lovedOnesRv.adapter = lovedOnesAdapter
        lovedOnesAdapter.setType("profile")

        val list3 = ArrayList<String>()
        list3.add("Nuclear middle class family with moderate values")
        list3.add("Request family details")

        lovedOnesAdapter.submitList(list3)

        dataBinding.hobbiesRv.layoutManager = LinearLayoutManager(this)
        dataBinding.hobbiesRv.adapter = hobbiesAdapter

        val list4 = ArrayList<Pair<String, String>>()
        list4.add(Pair("Hobbies", "Art / Handicraft"))
        list4.add(Pair("Interests", "Music"))
        list4.add(Pair("Music Genre", "Film songs"))
        list4.add(Pair("Sports/Fitness", "Cricket"))

        hobbiesAdapter.submitList(list4)

        dataBinding.preferencesRv.layoutManager = LinearLayoutManager(this)
        dataBinding.preferencesRv.adapter = preferencesAdapter

        val list5 = ArrayList<Pair<String, String>>()
        list5.add(Pair("Bride's Age ", "26 - 30yrs"))
        list5.add(Pair("Height", "5ft 3in - 5ft 9in"))
        list5.add(Pair("Marital status", "Unmarried"))
        list5.add(Pair("Mother tongue", "Malayalam"))
        list5.add(Pair("Physical status", "Normal"))
        list5.add(Pair("Eating habits", "Non-Vegetarian"))
        list5.add(Pair("Drinking habits", "Doesn't matter"))

        preferencesAdapter.submitList(list5)


        dataBinding.otherReligiousRv.layoutManager = LinearLayoutManager(this)
        dataBinding.otherReligiousRv.adapter = otherReligiousAdapter

        val list6 = ArrayList<Pair<String, String>>()
        list6.add(Pair("Religion", "Hindu"))
        list6.add(Pair("Division", "Nair"))
        list6.add(Pair("Caste", "Any"))

        otherReligiousAdapter.submitList(list6)

        dataBinding.otherProfessionalRv.layoutManager = LinearLayoutManager(this)
        dataBinding.otherProfessionalRv.adapter = otherProfessionalAdapter

        val list7 = ArrayList<Pair<String, String>>()
        list7.add(Pair("Education", "Ph.D, ANy Post Graduation"))
        list7.add(Pair("Employed in", "Any"))
        list7.add(Pair("Occupation", "Any"))
        list7.add(Pair("Annual income", "Any"))


        otherProfessionalAdapter.submitList(list6)


        dataBinding.locationRv.layoutManager = LinearLayoutManager(this)
        dataBinding.locationRv.adapter = locationAdapter

        val list8 = ArrayList<Pair<String, String>>()
        list8.add(Pair("Country", "Any"))
        list8.add(Pair("Residing State", "Any"))
        list8.add(Pair("Residing City", "Any"))
        list8.add(Pair("Citizenship", "Any"))



        locationAdapter.submitList(list8)
    }

    private fun subscribeData() {
        interestViewModel.sentInterest.observe(this) {
            hideProgress()
            if (it.safeGet()) {
                dataBinding.sendInterestBtn.background = resources.getDrawable(R.drawable.grey_stroke_bg)
                dataBinding.sendInterestBtn.setTextColor(resources.getColor(R.color.green))
                dataBinding.sendInterestBtn.text = "Interest sent"
                dataBinding.sendInterestBtn.isEnabled = false
                Toast.makeText(this, "Interest sent", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Sending interest failed", Toast.LENGTH_SHORT).show()
            }
        }

        profileViewModel.profileData.observe(this) {
            hideProgress()
            it?.let { profileData ->
                val list = ArrayList<String>()
                list.addIfNotNull(profileData.about_candidate)
                list.addIfNotNull(profileData.age.safeGet()+"years /"+it.height.safeGet())
                list.addIfNotNull(profileData.marital_status)
                list.addIfNotNull(profileData.education_category)
                list.addIfNotNull(profileData.place)
                list.addIfNotNull("Smoking : "+profileData.smoking)
                list.addIfNotNull("Drinking : "+profileData.drinking)
                list.addIfNotNull(profileData.employed_category)
                list.addIfNotNull(profileData.income_category)
                list.addIfNotNull(profileData.mobile)
                detailsAdapter.submitList(list)

                val list1 = ArrayList<Pair<String,String>>()
                list1.addIfNotNull("Religion", profileData.religion)
                list1.addIfNotNull("Caste", profileData.caste)
                list1.addIfNotNull("Star", profileData.star)
                religiousAdapter.submitList(list1)

                profileData.image?.let {  imageUrl ->
                    setImage(it.gender_id.safeGet(), imageUrl, dataBinding.otherPhotoImageView)
                    setImage(it.gender_id.safeGet(), imageUrl, dataBinding.profileImage)
                }

                if(profileData.interestStatus.isNullOrEmpty().not()){
                    dataBinding.sendInterestBtn.background = resources.getDrawable(R.drawable.grey_stroke_bg)
                    dataBinding.sendInterestBtn.setTextColor(resources.getColor(R.color.blue))
                        dataBinding.sendInterestBtn.text = profileData.interestStatus
                    dataBinding.sendInterestBtn.isEnabled = false
                }
            }
        }
    }

    private fun setImage(gender_id:String,imageUrl : String, imageView : ImageView ){
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(if (gender_id == "1") R.drawable.man_placeholder else R.drawable.female_placeholder)
            .error(if (gender_id == "1") R.drawable.man_placeholder else R.drawable.female_placeholder)

        Glide.with(this).load(imageUrl).apply(options)
            .into(imageView)
    }
}