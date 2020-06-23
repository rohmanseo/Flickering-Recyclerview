package com.rohman.fixrecyclerviewblink.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohman.fixrecyclerviewblink.R
import com.rohman.fixrecyclerviewblink.adapter.BookAdapter
import com.rohman.fixrecyclerviewblink.databinding.FragmentTabFirstBinding
import com.rohman.fixrecyclerviewblink.viewmodel.FirstViewmodel

class FirstTabFragment : Fragment() {

    private lateinit var binding: FragmentTabFirstBinding
    private lateinit var adapter: BookAdapter
    private val viewmodel: FirstViewmodel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_tab_first, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.getList().observe(requireActivity(), Observer { books ->
            if (books != null) {
                Log.d("viewmodelcallback",books.size.toString())
                binding.apply {
                    adapter = BookAdapter(requireContext())
                    adapter.setData(books)
                    recTabOne.adapter = adapter
                    recTabOne.layoutManager = LinearLayoutManager(requireContext())
                    recTabOne.setHasFixedSize(true)
                }
            }
        })

    }
}

