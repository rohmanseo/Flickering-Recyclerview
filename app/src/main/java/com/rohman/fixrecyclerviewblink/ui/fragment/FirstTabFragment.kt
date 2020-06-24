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
import com.rohman.fixrecyclerviewblink.adapter.BookAdapter
import com.rohman.fixrecyclerviewblink.databinding.FragmentTabFirstBinding
import com.rohman.fixrecyclerviewblink.viewmodel.FirstViewmodel

class FirstTabFragment : Fragment() {

    private lateinit var bookAdapter: BookAdapter
    private val viewmodel: FirstViewmodel by viewModels()

    companion object {
        private var firstTabView: View? = null
        private lateinit var binding: FragmentTabFirstBinding

    }

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
            binding.apply {
                bookAdapter = BookAdapter(requireActivity())
                recTabOne.apply {
                    layoutManager = LinearLayoutManager(requireActivity())
                    adapter = bookAdapter
                    setHasFixedSize(true)
                }
            }

            if (books != null) {
                binding.apply {
                    recTabOne.apply {
                        bookAdapter.setData(books)
                    }
                }
            }
        })

    }
}

