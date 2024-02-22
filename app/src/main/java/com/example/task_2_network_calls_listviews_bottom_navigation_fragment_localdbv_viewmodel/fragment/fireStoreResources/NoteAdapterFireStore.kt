package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.fireStoreResources

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.R
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.databinding.ItemNodesFirestoreBinding

class NoteAdapterFireStore : RecyclerView.Adapter<NoteAdapterFireStore.NoteViewHolder>() {

    private val notes = ArrayList<Node>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemNodesFirestoreBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_nodes_firestore, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int = notes.size

    fun updateNotes(newNotes: List<Node>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(private val binding: ItemNodesFirestoreBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Node) {
            binding.fireStoreData.text = note.text
        }
    }
}
