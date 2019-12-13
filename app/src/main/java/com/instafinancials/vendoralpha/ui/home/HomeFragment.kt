package com.instafinancials.vendoralpha.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.instafinancials.vendoralpha.*
import com.instafinancials.vendoralpha.apicall.network.ewaybillapi.EWayApiHelper
import com.instafinancials.vendoralpha.apicall.network.ewaybillapi.EwayGSTApi
import com.instafinancials.vendoralpha.apicall.network.homeapi.instabasic.InstaBasicApi
import com.instafinancials.vendoralpha.apicall.repositories.APIFactory
import com.instafinancials.vendoralpha.apicall.repositories.InstaRepo
import com.instafinancials.vendoralpha.ui.CameraActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var searchView: SearchView
    private lateinit var fab:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewPager = root.findViewById(R.id.view_pager)
        tabs = root.findViewById(R.id.tabs)
        searchView = root.findViewById(R.id.searchView)
        fab= root.findViewById(R.id.fab)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                if (s.length >= 15) {
                    Log.d("HomeFragment", "Text character is more than 15")
                }
                return false
            }
        })

        fab.setOnClickListener {
            val intent = Intent(activity, CameraActivity::class.java)
           // intent.putExtra("contentUid", contentUid)
            //intent.putExtra("imageUri", imageUri)
            //intent.putExtra("destinationUid", userUid)
           // startActivity(intent)


//            EwayGSTApi().validateGSTDetails("29AAGCC4475J2Z7", object : InstaRepo.IResponseStateListener {
//                override fun onSuccess() {
//                }
//
//                override fun onError() {
//                }
//            })
            InstaBasicApi().fetchImageItems("U72501KA2016PTC092387", object : InstaRepo.IResponseStateListener{
                override fun onSuccess() {
                }
                override fun onError() {
                }
            } )


        }

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter =
            SectionsPagerAdapter(
                activity!!,
                childFragmentManager
            )
        adapter.addFragment(BasicFragment(), "Basic")
        adapter.addFragment(DetailFragment(), "Detail")
        adapter.addFragment(LastFragment(), "Last")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        searchView.setQuery(AppPreferences.gstNum, false)

    }

}