package com.techxform.anywherematrimony.view.fragment

import android.R
import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.github.drjacky.imagepicker.ImagePicker
import com.techxform.anywherematrimony.data.NameIdObject
import com.techxform.anywherematrimony.databinding.FragmentCompleteRegistrationThreeBinding
import com.techxform.anywherematrimony.view.activity.CompleteRegistration
import com.techxform.anywherematrimony.viewmodel.AuthViewModel
import com.techxform.anywherematrimony.viewmodel.FiltersViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CompleteFragmentThree : BaseFragment() {
    private val LOG_TAG = javaClass.name
    private val filtersViewModel: FiltersViewModel by viewModel()
    private lateinit var binding: FragmentCompleteRegistrationThreeBinding
    private val authViewModel: AuthViewModel by sharedViewModel()
    var horoscopeUri : Uri? = null
    var idProof1 : Uri? = null
    var idProof2 : Uri? = null
    var currentSelected = HOROSCOPE_SELECTED
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompleteRegistrationThreeBinding.inflate(inflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonThird.setOnClickListener {
            (requireActivity() as? CompleteRegistration)?.displayFragmentD()
        }
        binding.horoscopeBtn.setOnClickListener {
            currentSelected = HOROSCOPE_SELECTED
            pickGalleryImage()
        }
        binding.idProof1Btn.setOnClickListener {
            currentSelected = ID_PROOF1_SELECTED
            pickGalleryImage()
        }
        binding.idProof2Btn.setOnClickListener {
            currentSelected = ID_PROOF2_SELECTED
            pickGalleryImage()
        }
        subscribeData()

    }


    private fun subscribeData() {
        authViewModel.registrationFiltersOutput.observe(viewLifecycleOwner) {
            context?.let { mContext ->
                val relationAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, (it.profile_created_by as MutableList<NameIdObject>))
                binding.relationToCandidateSpinner.adapter = relationAdapter

                val starAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, (it.star as MutableList<NameIdObject>))
                binding.starSpinner.adapter = starAdapter

                val horoscopeAdapter: ArrayAdapter<NameIdObject> =ArrayAdapter<NameIdObject>(mContext, R.layout.simple_spinner_item, getHoroscopeType())
                binding.horoscopeSpinner.adapter = horoscopeAdapter


            }
        }
    }

    private val galleryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                if (it.data?.hasExtra(ImagePicker.EXTRA_FILE_PATH)!!) {
                    val uri = it.data?.data
                    when (currentSelected) {
                        HOROSCOPE_SELECTED -> {
                            horoscopeUri = uri
                            uri?.path?.let { selectedUri ->
                                binding.horoscopeBtn.text = selectedUri.substring(selectedUri.lastIndexOf("/")+1,selectedUri.length)
                            }                        }
                        ID_PROOF1_SELECTED -> {
                            idProof1 = uri
                            uri?.path?.let { selectedUri ->
                                binding.idProof1Btn.text = selectedUri.substring(selectedUri.lastIndexOf("/")+1,selectedUri.length)
                            }                        }
                        ID_PROOF2_SELECTED -> {
                                idProof2 = uri
                            uri?.path?.let { selectedUri ->
                                binding.idProof2Btn.text = selectedUri.substring(selectedUri.lastIndexOf("/")+1,selectedUri.length)
                            }

                        }
                    }
                } else if (it.data?.hasExtra(ImagePicker.MULTIPLE_FILES_PATH)!!) {
                    val files = ImagePicker.getAllFile(it.data) as ArrayList<Uri>
                    if (files.size > 0) {
                        val uri = files[0] //first image
                    }
                } else {
                    Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show()
                }
            } else Toast.makeText(activity, "failed loading image", Toast.LENGTH_SHORT).show()

        }

        private fun pickGalleryImage() {
            galleryLauncher.launch(
                ImagePicker.with(activity as Activity)
                    .crop()
                    .galleryOnly()
                    .setMultipleAllowed(false)
//                .setOutputFormat(Bitmap.CompressFormat.WEBP)
                    .cropFreeStyle()
                    .galleryMimeTypes( // no gif images at all
                        mimeTypes = arrayOf(
                            "image/png",
                            "image/jpg",
                            "image/jpeg"
                        )
                    )
                    .createIntent()
            )
        }


    private fun getHoroscopeType(): MutableList<NameIdObject> {
        val horoscopeList = mutableListOf<NameIdObject>()
        val nameIdObject1 = NameIdObject()
        nameIdObject1.id = "1"
        nameIdObject1.name = "Chowa in 7th"
        horoscopeList.add(nameIdObject1)

        val nameIdObject2 = NameIdObject()
        nameIdObject2.id = "2"
        nameIdObject2.name = "Chowa in 8th"
        horoscopeList.add(nameIdObject2)

        val nameIdObject3 = NameIdObject()
        nameIdObject3.id = "3"
        nameIdObject3.name = "Normal"
        horoscopeList.add(nameIdObject3)

        val nameIdObject4 = NameIdObject()
        nameIdObject4.id = "4"
        nameIdObject4.name = "Paapam"
        horoscopeList.add(nameIdObject4)

        val nameIdObject5 = NameIdObject()
        nameIdObject5.id = "5"
        nameIdObject5.name = "Sudham"
        horoscopeList.add(nameIdObject5)
        return horoscopeList
    }

    companion object
    {
        const val HOROSCOPE_SELECTED = "HOROSCOPE_SELECTED"
        const val ID_PROOF1_SELECTED = "ID_PROOF1_SELECTED"
        const val ID_PROOF2_SELECTED = "ID_PROOF2_SELECTED"
    }
}