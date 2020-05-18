package com.nullbyte.superwiki.views.carousel.util

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.nullbyte.superwiki.views.carousel.CarouselRecyclerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nullbyte.superwiki.models.Image
import kotlinx.coroutines.*


object CarouselHelper {
    private var timerJob: Job? = null

    fun initViewPager(
        requireActivity: FragmentActivity, vpCarousel: ViewPager2, vpTab: TabLayout,
        list: List<Image>
    ) {

        val mList: ArrayList<String> = ArrayList<String>()

        for (banner in list) {
            mList.add(banner.url)
        }
        vpCarousel.adapter =
            CarouselRecyclerAdapter(list)//CarouselAdapter(fragmentManager = requireActivity.supportFragmentManager, banners = list)
        TabLayoutMediator(vpTab, vpCarousel) { tab, position ->
            tab.view.isClickable = false
        }.attach()
        vpCarousel.setPageTransformer(ZoomOutPageTransformer())
    }

    fun updateBannerImages(vp_banner: ViewPager2, list: List<Image>) {
        val carouselAdapter = vp_banner.adapter as CarouselRecyclerAdapter
        carouselAdapter.updateList(list)
    }

    fun autoSwipeViewPager(vp_banner: ViewPager2) {
        timerJob = CoroutineScope(Dispatchers.IO).launch {
            delay(3000L)
            withContext(Dispatchers.Main) {
                val adapterSize = vp_banner.adapter?.itemCount ?: 0
                var currentItem = vp_banner.currentItem
                if (currentItem >= adapterSize.minus(1)) {
                    currentItem = 0
                } else {
                    currentItem += 1
                }
                vp_banner.currentItem = currentItem

                autoSwipeViewPager(vp_banner)
            }
        }
    }

    fun removeViewPager() {
        if (timerJob != null) {
            val isCancel = timerJob?.isCancelled ?: true
            if (isCancel) timerJob?.cancel()
        }
    }

}