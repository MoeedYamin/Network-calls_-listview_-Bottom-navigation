package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.RoomDB
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.R
class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var userList = emptyList<User>()
    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.fireStoreData)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nodes_firestore, parent, false) // Replace with your item layout
        return UserViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.userName.text = currentUser.text
    }
    override fun getItemCount(): Int {
        return userList.size
    }
    fun setUserList(users: List<User>) {
        this.userList = users
        notifyDataSetChanged()
    }
}