package com.ahmadfebrianto.moviecatalogue.ui.home

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmadfebrianto.moviecatalogue.databinding.ActivityMainBinding
import com.ahmadfebrianto.moviecatalogue.utils.CatalogHelper

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CatalogHelper.application = applicationContext as Application

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
        supportActionBar?.elevation = 0f
    }


}