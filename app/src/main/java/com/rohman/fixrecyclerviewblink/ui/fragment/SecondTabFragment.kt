package com.rohman.fixrecyclerviewblink.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohman.fixrecyclerviewblink.R
import com.rohman.fixrecyclerviewblink.adapter.SecondAdapter
import com.rohman.fixrecyclerviewblink.databinding.FragmentTabSecondBinding
import com.rohman.fixrecyclerviewblink.model.BookModel
import com.rohman.fixrecyclerviewblink.viewmodel.SecondViewmodel

class SecondTabFragment : Fragment(), SecondAdapter.Interaction {

    private lateinit var binding: FragmentTabSecondBinding
    private lateinit var secondAdapter: SecondAdapter
    private val viewmodel: SecondViewmodel by viewModels()
    private var mListState: Parcelable? = null
    private lateinit var mLayoutmanager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab_second, container, false
        )
        mLayoutmanager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mListState = mLayoutmanager.onSaveInstanceState()
        outState.putParcelable("listState", mListState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            mListState = savedInstanceState.getParcelable("listState")
        }
    }

    override fun onResume() {
        super.onResume()
        if (mListState != null) {
            mLayoutmanager.onRestoreInstanceState(mListState)
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recTabTwo.apply {
                layoutManager = mLayoutmanager
                secondAdapter = SecondAdapter(this@SecondTabFragment)
                adapter = secondAdapter
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        viewmodel.getList().observe(requireActivity(), Observer { books ->
            if (books != null) {
                secondAdapter.submitList(books)
            }
        })
    }

    override fun onItemSelected(position: Int, item: BookModel) {

    }
}
