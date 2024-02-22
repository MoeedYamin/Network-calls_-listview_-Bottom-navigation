package com.example.task2_network_calls_listviews_bottom_navigation_fragment_local_dbv__viewmodel.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task2_network_calls_listviews_bottom_navigation_fragment_local_dbv__viewmodel.R
import com.example.task2_network_calls_listviews_bottom_navigation_fragment_local_dbv__viewmodel.databinding.ActivityMainScreenBinding
import com.example.task2_network_calls_listviews_bottom_navigation_fragment_local_dbv__viewmodel.databinding.FragmentFireStoreBinding




class FireStoreFragment : Fragment() {

    private lateinit var fireStoreBinding: FragmentFireStoreBinding
    private lateinit var view: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_fire_store, container, false)
        fireStoreBinding = FragmentFireStoreBinding.inflate(layoutInflater)
        setContentView(fireStoreBinding.root)


        return view
    }











}