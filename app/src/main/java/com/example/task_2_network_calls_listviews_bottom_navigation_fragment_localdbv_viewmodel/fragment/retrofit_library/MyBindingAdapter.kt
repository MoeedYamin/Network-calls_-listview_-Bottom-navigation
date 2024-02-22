package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library

import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.fireStoreResources.Node

object MyBindingAdapter {

//    @JvmStatic
//    @BindingAdapter("setTextToTextView")
//    fun setTextToTextView(textView: TextView, text: String?) {
//        textView.text = text
//    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setTextToTextView")
    fun setTextToTextView(textView: TextView, data: UserApi?) {
        textView.text = data?.first_name + data?.last_name
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setTextToTextView")
    fun setTextToTextView(textView: TextView, data: Node?) {
        textView.text = data?.text
        Log.d("MyBindingAdapter", "setTextToTextView: $data")

    }


}