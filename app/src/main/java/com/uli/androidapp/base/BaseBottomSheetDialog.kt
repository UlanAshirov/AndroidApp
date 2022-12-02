package com.uli.androidapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.uli.androidapp.R

typealias InflateBottom<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseBottomSheetDialog<VB : ViewBinding>(private val inflateBottom: InflateBottom<VB>) :
    BottomSheetDialogFragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding!!
    private var _controller: NavController? = null
    protected val controller get() = _controller!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navHostFragment =
            requireActivity()
                .supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        _controller = navHostFragment.navController
        _binding = inflateBottom.invoke(inflater, container, false)
        setupUI()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    protected abstract fun setupUI()
    protected open fun setupObserver() {}
}