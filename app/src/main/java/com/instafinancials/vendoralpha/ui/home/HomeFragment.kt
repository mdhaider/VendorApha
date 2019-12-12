package com.instafinancials.vendoralpha.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.instafinancials.vendoralpha.*
import com.instafinancials.vendoralpha.ui.CameraActivity

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var tabs: TabLayout
    private lateinit var searchView: EditText
    private lateinit var fab: FloatingActionButton

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
        fab = root.findViewById(R.id.fab)


        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        fab.setOnClickListener {
            val intent = Intent(activity, CameraActivity::class.java)
            // intent.putExtra("contentUid", contentUid)
            //intent.putExtra("imageUri", imageUri)
            //intent.putExtra("destinationUid", userUid)
            startActivity(intent)
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
        adapter.addFragment(GstTrackerFragment(), "GST Tracker")
        adapter.addFragment(InstaBasicFragment(), "InstaBasic")
        adapter.addFragment(InstaSummaryFragment(), "InstaSummary")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        searchView.setText(AppPreferences.gstNum)

    }

}