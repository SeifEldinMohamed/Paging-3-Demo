package com.seif.pagingv3demo.ui.homeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.seif.pagingv3demo.databinding.FragmentHomeBinding
import com.seif.pagingv3demo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
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
        initViewModel()
    }

    private fun setUpRecyclerView() {
        binding.rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        val decoration: DividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        binding.rvCharacters.addItemDecoration(decoration)
        binding.rvCharacters.adapter = rvAdapter
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this)[MainViewModel::class.java]
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                rvAdapter.submitData(it)
            }
        }
    }

}