package com.nullbyte.superwiki.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nullbyte.superwiki.R
import com.nullbyte.superwiki.base.BaseActivity
import com.nullbyte.superwiki.databinding.ActivityHomeBinding
import com.nullbyte.superwiki.utilities.addFragmentWithoutBackStack
import com.nullbyte.superwiki.utilities.popBackStack
import com.nullbyte.superwiki.utilities.replaceFragmentWithData
import com.nullbyte.superwiki.viewModels.HomeViewModel
import com.nullbyte.superwiki.views.fragments.LandingFragment
import com.nullbyte.superwiki.views.fragments.SearchResultFragment

class HomeActivity : BaseActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var homeBinding: ActivityHomeBinding
    private var isSearching: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        supportActionBar?.hide()

        addFragmentWithoutBackStack(LandingFragment(), R.id.container_layout)
        homeBinding.ibSearch.setOnClickListener {
            if (homeBinding.etSearch.text.isBlank())
                Toast.makeText(this, getString(R.string.empty_search_string), Toast.LENGTH_SHORT)
                    .show()
            else {
                isSearching = if (isSearching) {
                    val bundle = Bundle()
                    val searchedString = homeBinding.etSearch.text.toString()
                    bundle.putString("searchedString", searchedString)
                    replaceFragmentWithData(SearchResultFragment(), R.id.container_layout, bundle)
                    homeBinding.ibSearch.setImageResource(R.drawable.ic_cancel_black_24dp)
                    !isSearching
                } else {
                    homeBinding.etSearch.text.clear()
                    homeBinding.ibSearch.setImageResource(R.drawable.ic_search_grey_24dp)
                    popBackStack()
                    !isSearching
                }
            }
        }
    }

    override fun onBackPressed() {
        homeBinding.etSearch.text.clear()
        homeBinding.ibSearch.setImageResource(R.drawable.ic_search_grey_24dp)
        super.onBackPressed()
    }

    override fun onActivityStart() {
    }

    override fun onActivityResume() {
    }

    override fun onActivityStop() {
    }

    override fun onActivityPause() {
    }

    override fun onActivityDestroy() {
    }
}
