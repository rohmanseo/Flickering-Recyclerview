package com.rohman.fixrecyclerviewblink.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.rohman.fixrecyclerviewblink.R
import com.rohman.fixrecyclerviewblink.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        bottom_navigation.setupWithNavController(
            Navigation.findNavController(
                this,
                R.id.navHostFragment
            )
        )

    }
}
