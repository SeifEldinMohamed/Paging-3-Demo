package com.seif.pagingv3demo.ui.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seif.pagingv3demo.R
import com.seif.pagingv3demo.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    private val rvAdapter: CharactersAdapter by lazy { CharactersAdapter() }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        val decoration: DividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        binding.rvCharacters.addItemDecoration(decoration)
        binding.rvCharacters.adapter = rvAdapter
    }

}