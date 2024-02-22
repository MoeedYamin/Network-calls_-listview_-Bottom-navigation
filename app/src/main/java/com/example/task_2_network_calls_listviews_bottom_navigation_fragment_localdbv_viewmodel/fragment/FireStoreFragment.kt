package com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.FireStoreKeys
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.databinding.FragmentFireStoreBinding
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.fireStoreResources.Node
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.fireStoreResources.NoteAdapterFireStore
import com.example.task_2_network_calls_listviews_bottom_navigation_fragment_localdbv_viewmodel.fragment.fireStoreResources.NoteViewModel
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query


class FireStoreFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore
    private val nodes = ArrayList<Node>()
    private lateinit var noteAdapterFireStore: NoteAdapterFireStore
    private lateinit var viewModel: NoteViewModel
    private lateinit var fireStoreFragBinding: FragmentFireStoreBinding
    private val _fireStoreFragBinding get() = fireStoreFragBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fireStoreFragBinding = FragmentFireStoreBinding.inflate(inflater, container, false)
        return _fireStoreFragBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initilizingVariables()
        loadNotes()
        clickListeners()
    }

    private fun initilizingVariables() {

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        firestore = FirebaseFirestore.getInstance()
        noteAdapterFireStore = NoteAdapterFireStore()
        fireStoreFragBinding.fireStoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        fireStoreFragBinding.fireStoreRecyclerView.adapter = noteAdapterFireStore
        fireStoreFragBinding.fireStoreErrorMessage.visibility= View.GONE

    }

    private fun clickListeners() {
        fireStoreFragBinding.doneButtonFireStore.setOnClickListener(View.OnClickListener {

            if(fireStoreFragBinding.fireStoreEditText.text.isEmpty())
            {
                fireStoreFragBinding.fireStoreErrorMessage.visibility= View.VISIBLE
            }
            else
            {
                fireStoreFragBinding.progressBarFireStore.visibility = View.GONE
                val dataText = fireStoreFragBinding.fireStoreEditText.text.toString().trim()
                if (fireStoreFragBinding.fireStoreEditText.text.isNotEmpty()) {
                    addNoteToFirestore(dataText)
                    fireStoreFragBinding.fireStoreEditText.text.clear()
                    fireStoreFragBinding.progressBarFireStore.visibility = View.INVISIBLE
                }
            }
        })
    }

    private fun loadNotes() {
        fireStoreFragBinding.progressBarFireStore.visibility = View.VISIBLE
        firestore.collection(FireStoreKeys.COLLECTION_NAME)
            .orderBy(FireStoreKeys.TIME_STAMP_KEY, Query.Direction.DESCENDING)
            .addSnapshotListener { value, _ ->
                for (doc in value!!) {
                    Log.d("FireStoreFragment", "loadNotes: $nodes")
                    val text = doc.getString(FireStoreKeys.HASH_MAP_KEY) ?: ""
                    nodes.add(Node(text))
                }

                noteAdapterFireStore.updateNotes(nodes)

//                ViewModel.nodes.clear()
//                ViewModel.nodes.addAll(nodes)
                val noData = fireStoreFragBinding.noDataFoundFireStore
                fireStoreFragBinding.progressBarFireStore.visibility = View.GONE

                if (nodes.isEmpty()) {
                    noData.visibility = View.VISIBLE
                } else {
                    noData.visibility = View.GONE
                }

            }
        fireStoreFragBinding.fireStoreRecyclerView.apply {
            adapter=NoteAdapterFireStore()
            noteAdapterFireStore.notifyDataSetChanged()
            layoutManager=LinearLayoutManager(requireContext())
        }
    }

    private fun addNoteToFirestore(text: String) {
        val note = hashMapOf(
            FireStoreKeys.HASH_MAP_KEY to text,
            FireStoreKeys.TIME_STAMP_KEY to FieldValue.serverTimestamp()
        )

        firestore.collection(FireStoreKeys.COLLECTION_NAME)
            .add(note)
    }
}

