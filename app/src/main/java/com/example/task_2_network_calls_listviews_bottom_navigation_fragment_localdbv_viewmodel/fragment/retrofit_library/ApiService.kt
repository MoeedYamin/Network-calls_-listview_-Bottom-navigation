package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library

import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.ProjectConstants
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET(ProjectConstants.BASE_URL_END_POINT)
    fun getUsers(): Call<ApiResponse>
}
