package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.retrofit_library

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.R
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.databinding.ItemNodesApiBinding
class ApiRecyclerAdapter : RecyclerView.Adapter<ApiRecyclerAdapter.MyViewHolder>(), Filterable { fun setData(data: ArrayList<UserApi>) {
    this.items = data
    this.itemsFilterable = data
    notifyDataSetChanged()
}
    var items: ArrayList<UserApi> = ArrayList<UserApi>()
    var itemsFilterable: ArrayList<UserApi> = ArrayList<UserApi>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNodesApiBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_nodes_api, parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }
    override fun getItemCount(): Int {
        return items.size
    }
    inner class MyViewHolder(var binding: ItemNodesApiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserApi) {
            binding.apiNameTextView.text = data.first_name
            Log.d("ApiRecyclerAdapter", "bind: ${data.first_name}")

            val url = data.avatar
            Glide.with(binding.avatar)
                .load(url)
                .circleCrop()
                .placeholder(R.drawable.search)
                .fallback(R.drawable.search)
                .into(binding.avatar)
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                if (charSequence == null || charSequence.length < 0) {
                    filterResult.count = itemsFilterable.size
                    filterResult.values = itemsFilterable
                } else {
                    var searchChr = charSequence.toString()
                    var itemsmodel = ArrayList<UserApi>()
                    for (item in itemsFilterable) {
                        if (item.first_name.contains(searchChr)) {
                            itemsmodel.add(item)
                        }
                    }
                    filterResult.count = itemsmodel.size
                    filterResult.values = itemsmodel
                }
                return filterResult
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results!!.values as ArrayList<UserApi>
                notifyDataSetChanged()
            }
        }
    }
}




















class RecyclerViewAdapterApi(private var userList: List<UserApi>) : RecyclerView.Adapter<RecyclerViewAdapterApi.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val apiNameTextView: TextView = itemView.findViewById(R.id.apiNameTextView)
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nodes_api, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        val fullName = "${user.first_name} ${user.last_name}"
        holder.apiNameTextView.text = fullName

        // Load the avatar using Glide
        Glide.with(holder.avatarImageView)
            .load(user.avatar)
            .into(holder.avatarImageView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }
    fun updateData(newData: List<UserApi>) {
        userList = newData
        notifyDataSetChanged()
    }
}


