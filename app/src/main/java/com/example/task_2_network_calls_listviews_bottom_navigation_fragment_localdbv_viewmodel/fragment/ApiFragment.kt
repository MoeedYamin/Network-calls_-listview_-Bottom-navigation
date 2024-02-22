package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.ProjectConstants
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.R
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.databinding.FragmentApiBinding
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library.ApiRecyclerAdapter
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library.ApiResponse
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library.ApiService
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library.UserApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiFragment : Fragment() {
    private val retrofit = Retrofit.Builder()
        .baseUrl(ProjectConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(ApiService::class.java)
    private val userList = ArrayList<UserApi>()
    private lateinit var apiFragBinding: FragmentApiBinding
    private lateinit var adapter : ApiRecyclerAdapter

    private val _apiFragBinding get() = apiFragBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        apiFragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_api, container, false)
        return _apiFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initilizingVariables()
//        textChangeListeners()
        apiCall()

    }


    private fun apiCall() {

        val noData = apiFragBinding.noDataFoundApi
        val call = apiService.getUsers()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    userList.clear()
                    userList.addAll(response.body()?.data ?: emptyList())
                    adapter.setData(userList)
                    adapter.notifyDataSetChanged()
                    apiFragBinding.progressBarApi.visibility = View.GONE
                    if (userList.isEmpty()) {
                        noData.visibility = View.VISIBLE
                    } else {
                        noData.visibility = View.GONE
                    }

                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                // Handle the error here
            }
        })
    }

    private fun initilizingVariables() {

        adapter = ApiRecyclerAdapter()
        val recyclerView1 = apiFragBinding.apiRecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView1.layoutManager = layoutManager
        recyclerView1.adapter = adapter
        apiFragBinding.progressBarApi.visibility = View.VISIBLE


    }

//    private fun textChangeListeners() {
//        val searchEditText = apiFragBinding.apiEditText
//        searchEditText.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                // Filter the user list based on the search query
//                val query = s.toString().trim().toLowerCase()
//                val filteredList = userList.filter {
//                    it.first_name.toLowerCase().contains(query) || it.last_name.toLowerCase()
//                        .contains(query)
//                }
//                adapter.getFilter(filteredList)
//                val noData = apiFragBinding.noDataFoundApi
//                if (filteredList.isEmpty()) {
//                    noData.visibility = View.VISIBLE
//                } else {
//                    noData.visibility = View.GONE
//                }
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                // Not needed for this use case
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                // Not needed for this use case
//            }
//        })
//    }

}





