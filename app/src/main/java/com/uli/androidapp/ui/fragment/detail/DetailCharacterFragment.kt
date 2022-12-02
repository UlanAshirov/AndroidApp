package com.uli.androidapp.ui.fragment.detail

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uli.androidapp.base.BaseFragment
import com.uli.androidapp.databinding.FragmentDetailCharacterBinding
import com.uli.androidapp.ui.utils.loadImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailCharacterFragment :
    BaseFragment<FragmentDetailCharacterBinding>(FragmentDetailCharacterBinding::inflate) {
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[DetailCharacterViewModel::class.java]
    }

    override fun setupUI() {
        lifecycleScope.launchWhenCreated {
            viewModel.isConnected.collectLatest { isConnectivity ->
                if (isConnectivity) {
                    loadData()
                } else {
                    Toast.makeText(requireContext(), "Нет интернет соединения", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun loadData() {
        if (arguments != null) {
            val id = arguments?.getInt("id")
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.getCharacterById(id!!).collect {
                    binding.imgDetailCharacter.loadImage(binding.imgDetailCharacter, it.image)
                    binding.characterName.text = it.name
                    binding.characterLocation.text = it.location?.name
                    binding.characterStatus.text = it.status
                    binding.characterGender.text = it.gender
                }
            }
        } else
            Toast.makeText(requireContext(), "not id", Toast.LENGTH_SHORT).show()
    }
}
