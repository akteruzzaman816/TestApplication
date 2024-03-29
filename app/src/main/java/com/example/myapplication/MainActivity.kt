package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.akter.testlibrary.Adfinix
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup for ads
        Adfinix.showAds(binding.adView1)

        binding.btnFullAd.setOnClickListener {
            Adfinix.showFullScreenAds(this,184)
        }


    }
}