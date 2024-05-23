package com.dscreate_app.cryptocompare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dscreate_app.cryptocompare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}