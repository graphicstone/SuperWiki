package com.nullbyte.superwiki.views.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.base.BaseFragment
import kotlinx.android.synthetic.main.item_view_pager2.view.*

class CarouselViewFragment(url: String?) : BaseFragment() {
    private val imageUrl = url

    override fun initializeViews(view: View, savedInstanceState: Bundle?) {
        Glide.with(this)
                .load(imageUrl)
                .into(view.iv_view_pager2)
    }

    override fun getRootView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.item_view_pager2, container, false)
    }

    override fun onFragmentStart() {
    }

    override fun onFragmentStop() {
    }

    override fun onFragmentResume() {
    }

    override fun onFragmentPause() {
    }

    override fun onFragmentDestroy() {
    }
}