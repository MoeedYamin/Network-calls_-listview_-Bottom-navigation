package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel

import android.app.Application
import android.content.Context

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
    companion object{
        private lateinit var mContext:Context
        fun getContext():Context
        {
            return mContext
        }
    }
}