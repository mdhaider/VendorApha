package com.instafinancials.vendoralpha.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.databinding.FragmentProfileBinding
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.UserProfileResponse
import com.instafinancials.vendoralpha.network.RetrofitClient
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.ModelPreferences
import com.instafinancials.vendoralpha.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.dlg_progress.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var dialog: MaterialDialog
    private var mobNo:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mobNo = it.getString(Const.MOB_NO, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        dialog = MaterialDialog(activity!!).customView(R.layout.dlg_progress, scrollable = false)
            .cancelable(false)
        val customView = dialog.getCustomView()
        customView.txtTitle.text = getString(R.string.plz_wait)

        binding.btnBack.setOnClickListener {
          findNavController().navigate(R.id.action_profile_home_only)
        }

        if(!TextUtils.isEmpty(mobNo)){
            checkUserApi(mobNo!!)
        } else{
            findNavController().navigate(R.id.action_profile_home_only)
        }
    }

    private fun checkUserApi(mobNumber: String) {
        dialog.show()
        RetrofitClient.instance.getUserProfile(mobNumber)
            .enqueue(object : Callback<UserProfileResponse> {
                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    showToast(t.message!!)
                    dialog.dismiss()
                }

                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    dialog.dismiss()
                    if (response.code() == 200) {
                        val userProfileResponse = response.body()!!
                        binding.parViewProf.visibility=View.VISIBLE
                        ModelPreferences(activity!!).putObject(Const.PROF_USER, userProfileResponse)
                        setData(userProfileResponse)
                    } else {
                        showToast(response.body().toString())
                    }

                }
            })
    }

    private fun setData(userProfileResponse: UserProfileResponse) {
        binding.mName.setText(userProfileResponse.response?.userProfile?.userName)
        binding.mName.keyListener = null
        binding.mEmail.setText(userProfileResponse.response?.userProfile?.userEmail)
        binding.mEmail.keyListener = null
        binding.mPhone.setText(userProfileResponse.response?.userProfile?.mobileNo)
        binding.mPhone.keyListener = null
    }
}