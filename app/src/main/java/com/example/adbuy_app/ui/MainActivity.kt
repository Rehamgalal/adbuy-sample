package com.example.adbuy_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adbuy_app.R
import com.example.adbuy_app.viewmodel.AdBuyViewModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }
}