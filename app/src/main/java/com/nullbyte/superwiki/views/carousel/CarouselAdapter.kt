package com.nullbyte.superwiki.views.carousel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nullbyte.superwiki.models.Image

class CarouselAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    banners: List<Image>
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private var bannerUrl: List<Image> = banners
    private var listItems = bannerUrl.size

    override fun getItemCount(): Int {
        return listItems
    }

    override fun createFragment(position: Int): Fragment {
        return CarouselViewFragment(bannerUrl[position].url)
    }

    fun updateBannerList(banners: List<Image>) {
        bannerUrl = banners
        listItems = bannerUrl.size
        notifyDataSetChanged()
    }
}