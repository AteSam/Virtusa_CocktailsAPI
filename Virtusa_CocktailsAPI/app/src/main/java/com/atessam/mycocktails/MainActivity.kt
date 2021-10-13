package com.atessam.mycocktails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.atessam.mycocktails.core.observe
import com.atessam.mycocktails.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var toastHelper: com.atessam.mycocktails.application.ToasterHelper
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.atessam.mycocktails.R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = findNavController(com.atessam.mycocktails.R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        toastHelper.toastMessages.observe(this) {
            showToast(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}