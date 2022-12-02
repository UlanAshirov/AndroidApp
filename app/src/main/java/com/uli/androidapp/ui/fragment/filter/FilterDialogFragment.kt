package com.uli.androidapp.ui.fragment.filter

import androidx.lifecycle.ViewModelProvider
import com.uli.androidapp.base.BaseBottomSheetDialog
import com.uli.androidapp.databinding.FragmentFilterDialogBinding
import com.uli.androidapp.ui.fragment.character.CharacterViewModel

class FilterDialogFragment(private val filter: FilterCharacter) :
    BaseBottomSheetDialog<FragmentFilterDialogBinding>(FragmentFilterDialogBinding::inflate) {
    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CharacterViewModel::class.java]
    }

    override fun setupUI() {
        binding.btnSearch.setOnClickListener {
            val name = binding.etSearchName.text.toString()
            val status = when (binding.btnContainer.checkedRadioButtonId) {
                binding.btnChooseAlive.id -> "alive"
                binding.btnChooseDead.id -> "dead"
                binding.btnChooseUnknown.id -> "unknown"
                else -> ""
            }
            val gender = when (binding.btnContainer2.checkedRadioButtonId) {
                binding.btnChooseMale.id -> "male"
                binding.btnChooseFemale.id -> "female"
                binding.btnChooseUnknownGender.id -> "unknown"
                else -> ""
            }
            viewModel.setFilterCharacter(
                nameFilter = name, statusFilter = status, genderFilter = gender
            )
            filter.filterSearch()
            dismiss()
        }
    }

    interface FilterCharacter {
        fun filterSearch()
    }
}