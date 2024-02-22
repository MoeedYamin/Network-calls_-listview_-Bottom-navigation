package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addData(user: User)

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    fun readData(): LiveData<List<User>>
}