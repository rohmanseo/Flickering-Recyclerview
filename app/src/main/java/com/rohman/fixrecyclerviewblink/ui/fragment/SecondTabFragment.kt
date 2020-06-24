package com.rohman.fixrecyclerviewblink.ui.fragment

import android.os.Bundle
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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab_second, container, false
        )
        return binding.root
    }


    private fun initRecyclerView() {
        binding.apply {
            recTabTwo.apply {
                layoutManager = LinearLayoutManager(requireContext())
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
