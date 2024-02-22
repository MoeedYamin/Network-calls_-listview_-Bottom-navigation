package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.BaseApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel: ViewModel() {
    private val repository: UserRepository
    val readAllData: LiveData<List<User>>
    init {
        val userDao = UserDataBase.getDatabaseInstance(BaseApplication.getContext()).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}