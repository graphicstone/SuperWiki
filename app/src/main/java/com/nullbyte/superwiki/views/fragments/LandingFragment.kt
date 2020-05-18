package com.nullbyte.superwiki.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.adapters.CompleteListAdapter
import com.nullbyte.superwiki.base.BaseFragment
import com.nullbyte.superwiki.databinding.FragmentLandingBinding
import com.nullbyte.superwiki.models.CompleteList
import com.nullbyte.superwiki.models.Image
import com.nullbyte.superwiki.utilities.DisplayUtils
import com.nullbyte.superwiki.viewModels.LandingViewModel
import com.nullbyte.superwiki.views.carousel.util.CarouselHelper
import kotlinx.android.synthetic.main.home_carousel_vp.*

class LandingFragment : BaseFragment() {

    private lateinit var viewModel: LandingViewModel
    private lateinit var landingBinding: FragmentLandingBinding
    private var startingIndex = 0
    private var endingIndex = 19
    private val completeList: ArrayList<CompleteList> = ArrayList()
    private var singlePageList: ArrayList<CompleteList> = ArrayList()
    private var adapter: CompleteListAdapter? = null
    private lateinit var mAlertDialog: AlertDialog

    override fun getRootView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        landingBinding = FragmentLandingBinding.inflate(layoutInflater)
        return landingBinding.root
    }

    override fun initializeViews(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(LandingViewModel::class.java)
        viewModel.getCompleteListOfSuperheroes()
        mAlertDialog = DisplayUtils.createLoaderDialog(requireActivity())
        mAlertDialog.show()
        observeData()
        setCarouselView()
        landingBinding.btnNext.setOnClickListener {
            if (endingIndex < 553) {
                singlePageList.clear()
                startingIndex += 10
                endingIndex += 10
                for (i in startingIndex..endingIndex)
                    singlePageList.add(completeList[i])
                adapter!!.notifyDataSetChanged()
            } else
                Toast.makeText(requireContext(), getText(R.string.last_page), Toast.LENGTH_SHORT)
                    .show()
        }
        landingBinding.btnBack.setOnClickListener {
            if (startingIndex > 0) {
                singlePageList.clear()
                startingIndex -= 10
                endingIndex -= 10
                for (i in startingIndex..endingIndex)
                    singlePageList.add(completeList[i])
                adapter!!.notifyDataSetChanged()
            } else
                Toast.makeText(requireContext(), getText(R.string.first_page), Toast.LENGTH_SHORT)
                    .show()
        }
    }

    private fun setCarouselView() {
        val imageList: ArrayList<Image> = ArrayList()
        addImageInCarousel(imageList)
        CarouselHelper.initViewPager(requireActivity(), vp2_carousel, vp2_tab, imageList)
        CarouselHelper.autoSwipeViewPager(vp2_carousel)
    }

    private fun addImageInCarousel(imageList: java.util.ArrayList<Image>) {
        imageList.add(Image("https://pbs.twimg.com/media/EV8kuZNU8AAeaxB?format=jpg&name=medium"))
        imageList.add(Image("https://pbs.twimg.com/media/EJa6OekUYAAMUFT?format=jpg&name=medium"))
        imageList.add(Image("https://pbs.twimg.com/media/D6Oow8EU0AED0RD?format=jpg&name=large"))
        imageList.add(Image("https://pbs.twimg.com/media/EXEyuhpU4AA-U8C?format=jpg&name=large"))

    }

    private fun initRecyclerView(completeList: List<CompleteList>) {
        for (i in startingIndex..endingIndex)
            singlePageList.add(completeList[i])
        adapter = CompleteListAdapter(singlePageList)
        landingBinding.rvCompleteList.layoutManager = LinearLayoutManager(requireContext())
        landingBinding.rvCompleteList.adapter = adapter
        mAlertDialog.dismiss()
    }

    private fun observeData() {
        viewModel.completeListDetail.observe(requireActivity(), Observer { list ->
            completeList.addAll(list)
            initRecyclerView(list)
        })
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
