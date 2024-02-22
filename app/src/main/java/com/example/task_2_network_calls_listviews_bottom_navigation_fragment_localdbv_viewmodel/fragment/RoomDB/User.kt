package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val text: String
)
