package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB.User
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB.UserAdapter
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB.UserViewModel
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.R
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.databinding.FragmentLocalDbBinding

class LocalDbFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var localDbFragBinding: FragmentLocalDbBinding
    private lateinit var userAdapter: UserAdapter

    private var recyclerViewState: Parcelable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        localDbFragBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_local_db,container,false)
        return localDbFragBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializingVariables()
        clickListeners()
    }

    private fun clickListeners() {

        localDbFragBinding.doneButtonLocalDb.setOnClickListener {
            if (localDbFragBinding.localDbEditText.text.isEmpty()) {
                localDbFragBinding.localDbErrorMessage.visibility = View.VISIBLE

            } else {
                localDbFragBinding.localDbErrorMessage.visibility = View.GONE
                val dataToBeInserted = localDbFragBinding.localDbEditText.text.toString()
                if (dataToBeInserted.isNotEmpty()) {
                    val userStoredData = User(0, dataToBeInserted)
                    mUserViewModel.addUser(userStoredData)
                }
            }

        }
    }

    private fun initializingVariables() {
        mUserViewModel = ViewModelProvider(
            this,
            SavedStateViewModelFactory(requireActivity().application, this)
        )[UserViewModel::class.java]

        loadingData()
    }
    private fun loadingData() {
        localDbFragBinding.progressBarLocalDb.visibility = View.VISIBLE
        val noData = localDbFragBinding.noDataFoundLocalDb
        mUserViewModel.readAllData.observe(viewLifecycleOwner, { users ->
            users?.let {
                if (users.isEmpty()) {
                    localDbFragBinding.progressBarLocalDb.visibility = View.GONE
                    noData.visibility = View.VISIBLE
                } else {
                    localDbFragBinding.progressBarLocalDb.visibility = View.GONE
                    noData.visibility = View.GONE
                    userAdapter.setUserList(it)
                }
            }
        })
        userAdapter = UserAdapter()
        recyclerViewState?.let {
            localDbFragBinding.localDbRecyclerView.layoutManager?.onRestoreInstanceState(it)
        }
        localDbFragBinding.localDbRecyclerView.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        localDbFragBinding.localDbRecyclerView.adapter = userAdapter // Set the adapter
        localDbFragBinding.localDbRecyclerView.layoutManager = LinearLayoutManager(requireContext())


    }
}
