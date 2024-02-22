package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.RoomDbKeys

@Database(entities = [User::class], version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
//        @Volatile
        private var INSTANCE: UserDataBase? = null
        fun getDatabaseInstance(context: Context): UserDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    RoomDbKeys.USER_TABLE
                )
                    .fallbackToDestructiveMigration() // only temporary
                    .build()
            }
            return INSTANCE as UserDataBase
        }
    }
}
