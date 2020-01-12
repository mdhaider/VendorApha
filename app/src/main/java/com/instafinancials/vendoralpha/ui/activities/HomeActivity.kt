package com.instafinancials.vendoralpha.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.navigation.NavigationView
import com.instafinancials.vendoralpha.BuildConfig
import com.instafinancials.vendoralpha.R
import com.instafinancials.vendoralpha.extensions.showToast
import com.instafinancials.vendoralpha.models.UserProfileResponse
import com.instafinancials.vendoralpha.shared.AppPreferences
import com.instafinancials.vendoralpha.shared.Const
import com.instafinancials.vendoralpha.shared.ModelPreferences
import com.instafinancials.vendoralpha.shared.VendorApp


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var navView: NavigationView
    private lateinit var header: View
    private lateinit var user: UserProfileResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupNavigationController()
    }

    private fun setupNavigationController() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        navController = findNavController(R.id.nav_host_fragment)

        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController);
        navView.setNavigationItemSelectedListener(this)

        setUpHeader()
    }

    private fun showlogoutDialog() {
        MaterialDialog(this).show {
            message(R.string.logout_msg)
            positiveButton(R.string.logout_pos) { dialog ->
                logOut()
            }
            negativeButton(R.string.logout_neg) { dialog ->
                dialog.dismiss()
            }
        }
    }

    private fun logOut() {
        try {
            AppPreferences.isLoggedIn = false
            ModelPreferences(this).putObject(Const.PROF_USER, null)
            showToast("You are Signed out!")
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        } catch (e: Exception) {
            Log.e("ProfileFrag", "onClick: Exception " + e.message, e)
        }
    }


    private fun setUpHeader() {
        header = navView.getHeaderView(0)
        val version = header.findViewById(R.id.tvVersion) as TextView
        val login = header.findViewById(R.id.tvLogin) as TextView
        val guest = header.findViewById(R.id.tvGuest) as TextView
        val loggedIn = header.findViewById(R.id.tvLoggedIn) as View
        val imgEdit = header.findViewById(R.id.imgEdit) as ImageView
        val name = header.findViewById(R.id.tvName) as TextView
        val email = header.findViewById(R.id.tvEmail) as TextView

        if (AppPreferences.isLoggedIn) {
            login.text = getString(R.string.log_out)
        } else {
            login.text = getString(R.string.log_in)
        }

        login.setOnClickListener {
            if (AppPreferences.isLoggedIn) {
                showlogoutDialog()
            } else {
                startActivity(Intent(VendorApp.instance, LoginActivity::class.java))
                finish()
            }

        }

        if (AppPreferences.isLoggedIn) {
            user = ModelPreferences(this).getObject(
                Const.PROF_USER,
                UserProfileResponse::class.java
            )!!

            if (!TextUtils.isEmpty(user.response?.userProfile?.userName)) {
                guest.visibility = View.GONE
                name.text = user.response?.userProfile?.userName
                email.text = user.response?.userProfile?.userEmail
                name.setTextColor(resources.getColor(R.color.white))

            } else {
                // binding.tvName.text = "Guest User"
                // binding.tvName.setBackgroundResource(R.drawable.rect_box_color)
            }

        } else {
            guest.text = "Guest User"
            loggedIn.visibility = View.GONE
            guest.setBackgroundResource(R.drawable.rect_box_color)
        }

        version.text = "v" + BuildConfig.VERSION_NAME

        imgEdit.setOnClickListener {
            navController.navigate(R.id.nav_profile2)
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_rate -> {
                //  newGame()
                true
            }
            R.id.action_report -> {
                //  showHelp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawerLayout
        );
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            return super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        menuItem.isChecked = true
        drawerLayout.closeDrawers()
        val id = menuItem.itemId
        when (id) {
            R.id.nav_home -> navController.navigate(R.id.nav_home1)
            R.id.nav_help -> navController.navigate(R.id.nav_support)
            R.id.nav_tou -> navController.navigate(R.id.nav_tou)
        }
        return true
    }
}
