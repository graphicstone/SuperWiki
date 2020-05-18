package com.nullbyte.superwiki.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return getRootView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        onFragmentStart()
    }

    override fun onResume() {
        super.onResume()
        onFragmentResume()
    }

    override fun onPause() {
        super.onPause()
        onFragmentPause()
    }

    override fun onStop() {
        super.onStop()
        onFragmentStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        onFragmentDestroy()
    }

    protected abstract fun getRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    protected abstract fun initializeViews(view: View, savedInstanceState: Bundle?)
    protected abstract fun onFragmentStart()
    protected abstract fun onFragmentStop()
    protected abstract fun onFragmentResume()
    protected abstract fun onFragmentPause()
    protected abstract fun onFragmentDestroy()

}