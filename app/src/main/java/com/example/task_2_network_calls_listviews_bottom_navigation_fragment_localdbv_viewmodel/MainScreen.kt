package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.databinding.ActivityMainScreenBinding
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.ApiFragment
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.FireStoreFragment
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.LocalDbFragment

class MainScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializingBinding()
        initializingFragmentLayout()
        clickListeners()
    }

    private fun clickListeners() {
        bottomNavigationBar()

    }

    private fun bottomNavigationBar() {
        var selectedTab = 1
        binding.fireStoreLayout.setOnClickListener(View.OnClickListener {

            if (selectedTab != 1) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragments, FireStoreFragment::class.java, null).commit()
                binding.apiText.visibility = View.GONE
                binding.localDbText.visibility = View.GONE
                binding.apiImage.setImageResource(R.drawable.api)
                binding.localDbImage.setImageResource(R.drawable.local_db)
                binding.apiLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))
                binding.localDbLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))
                binding.fireStoreText.visibility = View.VISIBLE
                binding.fireStoreImage.setImageResource(R.drawable.firestore_selected)
                binding.fireStoreLayout.setBackgroundResource(R.drawable.navigation_bar_icon_bg)
                val scaleAnimation = ScaleAnimation(
                    0.8f,
                    1.0f,
                    1f,
                    1f,
                    Animation.RELATIVE_TO_SELF,
                    0.0f,
                    Animation.RELATIVE_TO_SELF,
                    0.0f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                binding.fireStoreLayout.startAnimation(scaleAnimation)
                selectedTab = 1
            }


        })
        binding.apiLayout.setOnClickListener(View.OnClickListener {

            if (selectedTab != 2) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragments, ApiFragment::class.java, null).commit()
                binding.fireStoreText.visibility = View.GONE
                binding.localDbText.visibility = View.GONE
                binding.fireStoreImage.setImageResource(R.drawable.firestore)
                binding.localDbImage.setImageResource(R.drawable.local_db)
                binding.fireStoreLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))
                binding.localDbLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))
                binding.apiText.visibility = View.VISIBLE
                binding.apiImage.setImageResource(R.drawable.api_selected)
                binding.apiLayout.setBackgroundResource(R.drawable.navigation_bar_icon_bg)

                val scaleAnimation = ScaleAnimation(
                    0.8f,
                    1.0f,
                    1f,
                    1f,
                    Animation.RELATIVE_TO_SELF,
                    0.0f,
                    Animation.RELATIVE_TO_SELF,
                    0.0f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                binding.apiLayout.startAnimation(scaleAnimation)

                selectedTab = 2
            }


        })
        binding.localDbLayout.setOnClickListener(View.OnClickListener {

            if (selectedTab != 3) {
                supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragments, LocalDbFragment::class.java, null).commit()
                binding.fireStoreText.visibility = View.GONE
                binding.apiText.visibility = View.GONE

                binding.fireStoreImage.setImageResource(R.drawable.firestore)
                binding.apiImage.setImageResource(R.drawable.api)

                binding.fireStoreLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))
                binding.apiLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))

                binding.localDbText.visibility = View.VISIBLE
                binding.localDbImage.setImageResource(R.drawable.local_db_selected)
                binding.localDbLayout.setBackgroundResource(R.drawable.navigation_bar_icon_bg)

                val scaleAnimation = ScaleAnimation(
                    0.8f,
                    1.0f,
                    1f,
                    1f,
                    Animation.RELATIVE_TO_SELF,
                    0.0f,
                    Animation.RELATIVE_TO_SELF,
                    0.0f
                )
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                binding.localDbLayout.startAnimation(scaleAnimation)

                selectedTab = 3
            }
        })
    }

    private fun initializingBinding() {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    private fun initializingFragmentLayout() {
        supportFragmentManager.beginTransaction().setReorderingAllowed(true)
            .replace(R.id.fragments, FireStoreFragment::class.java, null).commit()
    }

}