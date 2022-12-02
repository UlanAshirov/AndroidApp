package com.uli.androidapp.ui.fragment.character

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.uli.androidapp.R
import com.uli.androidapp.base.BaseFragment
import com.uli.androidapp.databinding.FragmentCharacterBinding
import com.uli.androidapp.ui.fragment.character.adapter.CharacterAdapter
import com.uli.androidapp.ui.fragment.filter.FilterDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<FragmentCharacterBinding>(FragmentCharacterBinding::inflate),
    CharacterAdapter.SendIdCharacterListener, FilterDialogFragment.FilterCharacter {

    private lateinit var adapter: CharacterAdapter
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
    }

    override fun setupUI() {
        adapter = CharacterAdapter(this)
        binding.rvCharacter.adapter = adapter
        lifecycleScope.launchWhenCreated {
            viewModel.isConnected.collectLatest { isConnectivity ->
                if (isConnectivity) {
                    loadData()
                } else {
                    Toast.makeText(requireContext(), "Нет интернет соединения", Toast.LENGTH_SHORT)
                        .show()
                    viewModel.getAllCharactersResult().collectLatest { resultFromLocal ->
                        adapter.submitData(PagingData.from(resultFromLocal))
                    }
                }
            }
        }
    }

    override fun setupObserver() {
        super.setupObserver()
        openFilter()
    }

    private fun openFilter() {
        binding.btnFilter.setOnClickListener {
            FilterDialogFragment(this).show(requireActivity().supportFragmentManager, "")
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            viewModel.getCharacter().collectLatest { resultFromNetwork ->
                adapter.submitData(resultFromNetwork)
            }
        }
    }

    override fun sendId(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        controller.navigate(R.id.detailCharacterFragment, bundle)
    }

    override fun filterSearch() {
        loadData()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.addCharacterResult(adapter.snapshot().items)
        }
    }
}